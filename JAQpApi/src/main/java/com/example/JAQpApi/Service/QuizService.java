package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Entity.Quiz.ImageMetadata;
import com.example.JAQpApi.Entity.Quiz.Question;
import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.Quiz.Tag;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.ImageMetadataRepo;
import com.example.JAQpApi.Repository.QuizRepo;
import com.example.JAQpApi.Repository.TagRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService
{
    private final QuizRepo quizRepo;
    private final ImageMetadataRepo imageMetadataRepo;
    private final AuthService authService;
    private final ImageService imageService;
    private final QuestionService questionService;

    private final UserService userService;

    private final TagRepo tagRepo;

    private QuizResponse QuizResponseFactory(Quiz _quiz)
    {

        List<String> tags = null;
        if(_quiz.getTags() != null)
        {
            for (Tag tag : _quiz.getTags())
            {
                tags.add(tag.getTagId());
            }
        }
        return QuizResponse.builder()
                .id(_quiz.getId())
                .description(_quiz.getDescription())
                .image_name((_quiz.getThumbnail() != null) ? _quiz.getThumbnail().getName() : null)
                .name(_quiz.getName())
                .tags(tags)
                .isPublic(_quiz.getIsPublic())
                .build();
    }

    public QuizService(QuizRepo quizRepo, ImageMetadataRepo imageMetadataRepo, AuthService authService, ImageService imageService, @Lazy QuestionService questionService, TagRepo tagRepo, UserService userService)
    {
        this.quizRepo = quizRepo;
        this.imageMetadataRepo = imageMetadataRepo;
        this.authService = authService;
        this.imageService = imageService;
        this.questionService = questionService;
        this.tagRepo = tagRepo;
        this.userService = userService;
    }

    public Optional<Quiz> ValidateAccessAndGetQuiz(String _token, Integer _id) throws AccessDeniedException, NotFoundException
    {
        Quiz result = quizRepo.findById(_id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        if (Objects.equals(authService.GetUserByToken(_token).getId(), result.getOwner().getId()))
        {
            return Optional.of(result);
        }
        throw new AccessDeniedException("Access denied");
    }

    public QuizResponse CreateQuiz(String _token, QuizCreateRequest _request) throws NotFoundException, ImageException
    {
        ImageMetadata thumnail = null;
        if (_request.getThumbnail() != null)
        {
            thumnail = imageMetadataRepo.findById(imageService.UploadFile(_request.getThumbnail(), _token)).orElseThrow(() -> new ImageException("Unknown image error"));
        }
        List<Tag> tags = (_request.getTags() == null) ? null : (List<Tag>) tagRepo.findAllById(_request.getTags());
        User owner = authService.GetUserByToken(_token);
        Quiz quiz = Quiz.builder()
                .description(_request.getDescription())
                .name(_request.getName())
                .thumbnail(thumnail)
                .owner(owner)
                .tags(tags)
                .isPublic(false)
                .build();
        quiz = quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }

    public OwnedQuizListResponse GetOwnedQuiz(String _token) throws NotFoundException
    {
        User owner = authService.GetUserByToken(_token);
        List<QuizData> list = new ArrayList<>();
        for (Quiz quiz : quizRepo.findAllByOwner(owner))
        {
            list.add(QuizData.builder()
                    .id(quiz.getId())
                    .name(quiz.getName())
                    .image((quiz.getThumbnail() == null) ? null : quiz.getThumbnail().getName())
                    .description(quiz.getDescription())
                    .build());
        }
        return new OwnedQuizListResponse(list);
    }


    public OwnedQuizListResponse GetOwnedQuiz(Integer _id) throws NotFoundException
    {
        User owner = userService.GetUserById(_id);
        List<QuizData> list = new ArrayList<>();
        for (Quiz quiz : quizRepo.findAllByOwner(owner))
        {
            if (!quiz.getIsPublic())
            {
                continue;
            }
            list.add(QuizData.builder()
                    .id(quiz.getId())
                    .name(quiz.getName())
                    .image((quiz.getThumbnail() == null) ? null : quiz.getThumbnail().getName())
                    .description(quiz.getDescription())
                    .build());
        }
        return new OwnedQuizListResponse(list);
    }
    public QuestionsOfQuizResponse GetQuestionsOfQuiz(Integer _id) throws NotFoundException
    {
        return QuestionsOfQuizResponse.toDto(quizRepo.findById(_id).orElseThrow(() -> new NotFoundException("")).getQuestions());
    }

    public QuizResponse GetQuiz(Integer _id) throws NotFoundException
    {
        Quiz quiz = quizRepo.findById(_id).orElseThrow(() -> new NotFoundException(""));
        return QuizResponseFactory(quiz);
    }


    public void DeleteQuiz(String _token, Integer _id) throws AccessDeniedException, NotFoundException, ImageException
    {
        Quiz quiz = ValidateAccessAndGetQuiz(_token, _id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        List<Question> questions = quiz.getQuestions();
        ImageMetadata imageMetadata = quiz.getThumbnail();
        for (Question question : questions)
        {
            questionService.DeleteQuestion(_token, question.getId());
        }
        quizRepo.delete(quiz);
        imageService.DeleteImage(imageMetadata, _token);
    }

    private Quiz ChangeQuiz(String _token, Integer _id, String _name, String _description, List<String> _tags) throws AccessDeniedException, NotFoundException
    {
        Quiz quiz = ValidateAccessAndGetQuiz(_token, _id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        quiz.setDescription(_description);
        quiz.setName(_name);
        quiz.setTags((List<Tag>) tagRepo.findAllById(_tags));
        return quiz;
    }

    public QuizResponse ChangeQuiz(String _token, QuizCreateRequest _request, Integer _id) throws AccessDeniedException, NotFoundException, ImageException
    {
        Quiz quiz = ChangeQuiz(_token, _id, _request.getName(), _request.getDescription(), _request.getTags());
        ImageMetadata imageMetadata = quiz.getThumbnail();
        quiz.setThumbnail(null);
        quizRepo.save(quiz);
        imageMetadata = imageService.ChangeImage(imageMetadata, _token, _request.getThumbnail());
        quiz.setThumbnail(imageMetadata);
        quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }

    public QuizResponse ChangeQuiz(String _token, QuizChangeRequest _request, Integer _id) throws AccessDeniedException, NotFoundException
    {
        Quiz quiz = ChangeQuiz(_token, _id, _request.getName(), _request.getDescription(), _request.getTags());
        quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }

    public QuizResponse TogglePublic(String _token, Integer _id) throws AccessDeniedException, NotFoundException
    {
        Quiz quiz = ValidateAccessAndGetQuiz(_token, _id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        quiz.setIsPublic(!quiz.getIsPublic());
        quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }
}
