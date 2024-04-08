package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.OwnedQuizListResponse;
import com.example.JAQpApi.DTO.QuizCreateRequest;
import com.example.JAQpApi.DTO.QuizCreateResponse;
import com.example.JAQpApi.DTO.QuizData;
import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.Quiz;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Repository.ImageMetadataRepo;
import com.example.JAQpApi.Repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService
{
    private final QuizRepo quizRepo;
    private final ImageMetadataRepo imageMetadataRepo;

    private final AuthService authService;
    private final ImageService imageService;

    public QuizCreateResponse CreateQuiz(String _token, QuizCreateRequest _request) throws UserNotFoundExeption, ImageException
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
                .quizId(quiz.getId())
                .description(quiz.getDescription())
                .imageName(quiz.getThumnail().getName())
                .name(quiz.getName())
                .build();
    }

    public OwnedQuizListResponse GetOwnedQuiz(String _token) throws UserNotFoundExeption
    {
        User owner = authService.GetUserByToken(_token);
        List<QuizData> list = new ArrayList<>();
        for (Quiz quiz : quizRepo.findAllByOwner(owner))
        {
            list.add(QuizData.builder()
                    .id(quiz.getId())
                    .name(quiz.getName())
                    .build());
        }
        return new OwnedQuizListResponse(list);
    }
}
