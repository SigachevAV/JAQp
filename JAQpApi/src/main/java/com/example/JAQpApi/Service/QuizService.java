package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.Quiz.ImageMetadata;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.ImageMetadataRepo;
import com.example.JAQpApi.Repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService
{
    private final QuizRepo quizRepo;
    private final ImageMetadataRepo imageMetadataRepo;

    private final AuthService authService;
    private final ImageService imageService;

    public Optional<Quiz> ValidateAccessAndGetQuiz(String _token, Integer _id) throws AccessDeniedException, NotFoundException
    {
        Quiz result = quizRepo.findById(_id).orElseThrow(() -> new NotFoundException("Quiz not found"));
        if (Objects.equals(authService.GetUserByToken(_token).getId(), result.getOwner().getId()))
        {
            return Optional.of(result);
        }
        throw new AccessDeniedException("Access denied");
    }

    public QuizCreateResponse CreateQuiz(String _token, QuizCreateRequest _request) throws NotFoundException, ImageException
    {
        ImageMetadata thumnail = imageMetadataRepo.findById(imageService.UploadFile(_request.getThumnail(), _token)).orElseThrow(() -> new ImageException("Unknown image error"));
        User owner = authService.GetUserByToken(_token);
        Quiz quiz = Quiz.builder()
                .description(_request.getDescription())
                .name(_request.getName())
                .thumnail(thumnail)
                .owner(owner)
                .build();
        quiz = quizRepo.save(quiz);
        return QuizCreateResponse.builder()
                .quizId(quiz.getQuiz_id())
                .description(quiz.getDescription())
                .imageName(quiz.getThumnail().getName())
                .name(quiz.getName())
                .build();
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
        return QuizResponse.builder()
                .description(quiz.getDescription())
                .id(quiz.getQuiz_id())
                .image_name(quiz.getThumnail().getName())
                .name(quiz.getName())
                .build();
    }
}
