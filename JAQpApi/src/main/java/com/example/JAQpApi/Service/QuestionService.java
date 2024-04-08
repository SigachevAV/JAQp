package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.QuestionCreateRequest;
import com.example.JAQpApi.DTO.QuestionCreateResponse;
import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.Question;
import com.example.JAQpApi.Entity.Quiz;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.NotFoundException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Repository.QuestionRepo;
import com.example.JAQpApi.Repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService
{
    private final ImageService imageService;
    private final QuestionRepo questionRepo;
    private final QuizService quizService;


    public QuestionCreateResponse AddQuestion(String _token, QuestionCreateRequest _request) throws UserNotFoundExeption, NotFoundException, ImageException
    {
        Quiz quiz = quizService.ValidateAccessAndGetQuiz(_token, _request.getQuiz_id()).orElseThrow(() -> new NotFoundException(""));
        ImageMetadata image = null;
        String imageName = null;
        if (_request.getImage() != null)
        {
            image = imageService.GetImageMetadata(imageService.UploadFile(_request.getImage(), _token));
            imageName = image.getName();
        }
        Question question = Question.builder()
                .image(image)
                .description(_request.getContent())
                .quiz(quiz)
                .build();
        questionRepo.save(question);
        return QuestionCreateResponse.builder()
                .content(question.getDescription())
                .id(question.getQuestion_Id())
                .imageName(imageName)
                .build();
    }
}
