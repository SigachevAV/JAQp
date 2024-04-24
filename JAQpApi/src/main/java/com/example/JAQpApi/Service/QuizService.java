package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.Question;
import com.example.JAQpApi.Entity.Quiz;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.ImageMetadataRepo;
import com.example.JAQpApi.Repository.QuizRepo;
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

    private QuizResponse QuizResponseFactory(Quiz _quiz)
    {
        return QuizResponse.builder()
                .id(_quiz.getQuiz_id())
                .description(_quiz.getDescription())
                .image_name((_quiz.getThumnail() != null) ? _quiz.getThumnail().getName() : null)
                .name(_quiz.getName())
                .build();
    }

    public QuizService(QuizRepo quizRepo, ImageMetadataRepo imageMetadataRepo, AuthService authService, ImageService imageService, @Lazy QuestionService questionService)
    {
        this.quizRepo = quizRepo;
        this.imageMetadataRepo = imageMetadataRepo;
        this.authService = authService;
        this.imageService = imageService;
        this.questionService = questionService;
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
        if (_request.getThumnail() != null)
        {
            thumnail = imageMetadataRepo.findById(imageService.UploadFile(_request.getThumnail(), _token)).orElseThrow(() -> new ImageException("Unknown image error"));
        }
        User owner = authService.GetUserByToken(_token);
        Quiz quiz = Quiz.builder()
                .description(_request.getDescription())
                .name(_request.getName())
                .thumnail(thumnail)
                .owner(owner)
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
                    .id(quiz.getQuiz_id())
                    .name(quiz.getName())
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
        ImageMetadata imageMetadata = quiz.getThumnail();
        for (Question question : questions)
        {
            questionService.DeleteQuestion(_token, question.getQuestion_Id());
        }
        quizRepo.delete(quiz);
        imageService.DeleteImage(imageMetadata, _token);
    }

    private Quiz ChangeQuiz(String _token, Integer _id, String _name, String _description) throws AccessDeniedException, NotFoundException
    {
        Quiz quiz = ValidateAccessAndGetQuiz(_token, _id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        quiz.setDescription(_description);
        quiz.setName(_name);
        return quiz;
    }

    public QuizResponse ChangeQuiz(String _token, QuizCreateRequest _request, Integer _id) throws AccessDeniedException, NotFoundException, ImageException
    {
        Quiz quiz = ChangeQuiz(_token, _id, _request.getName(), _request.getDescription());
        ImageMetadata imageMetadata = quiz.getThumnail();
        quiz.setThumnail(null);
        quizRepo.save(quiz);
        imageMetadata = imageService.ChangeImage(imageMetadata, _token, _request.getThumnail());
        quiz.setThumnail(imageMetadata);
        quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }

    public QuizResponse ChangeQuiz(String _token, QuizChangeRequest _request, Integer _id) throws AccessDeniedException, NotFoundException
    {
        Quiz quiz = ChangeQuiz(_token, _id, _request.getName(), _request.getDescription());
        quizRepo.save(quiz);
        return QuizResponseFactory(quiz);
    }
}
