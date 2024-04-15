package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.QuestionCreateRequest;
import com.example.JAQpApi.DTO.QuestionCreateResponse;
import com.example.JAQpApi.Entity.Question;
import com.example.JAQpApi.Entity.Quiz;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService
{
    private final ImageService imageService;
    private final QuestionRepo questionRepo;
    private final QuizService quizService;

    public Optional<Question> ValidateAccessAndGetQuestion(String _token, Integer _id) throws NotFoundException, AccessDeniedException
    {
        Question question = questionRepo.findById(_id).orElseThrow(() -> new NotFoundException(""));
        if(quizService.ValidateAccessAndGetQuiz(_token ,question.getQuiz().getQuiz_id()).isPresent())
        {
            return Optional.of(question);
        }
        else
        {
            return Optional.empty();
        }
    }

    public QuestionCreateResponse AddQuestion(String _token, QuestionCreateRequest _request) throws NotFoundException, ImageException, AccessDeniedException
    {
        Quiz quiz = quizService.ValidateAccessAndGetQuiz(_token, _request.getQuiz_id()).orElseThrow(() -> new NotFoundException(""));
        ImageService.ImageMetadataWithName imageMetadataWithName = imageService.HandleNullableImageRequest(_token, _request.getImage());
        Question question = Question.builder()
                .image(imageMetadataWithName.getImageMetadata())
                .description(_request.getContent())
                .quiz(quiz)
                .build();
        questionRepo.save(question);
        return QuestionCreateResponse.builder()
                .content(question.getDescription())
                .id(question.getQuestion_Id())
                .imageName(imageMetadataWithName.getImageName())
                .build();
    }
}
