package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.AnswerCreateRequest;
import com.example.JAQpApi.DTO.AnswerCreateResponse;
import com.example.JAQpApi.Entity.Answer;
import com.example.JAQpApi.Entity.Question;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.AnswerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService
{
    private final AnswerRepo answerRepo;
    private final QuestionService questionService;

    private final ImageService imageService;

    public AnswerCreateResponse AddAnswer(String _token, AnswerCreateRequest _request) throws NotFoundException, AccessDeniedException, ImageException
    {
        Question question = questionService.ValidateAccessAndGetQuestion(_token, _request.getQuestion_id()).orElseThrow(() -> new NotFoundException(("")));
        ImageService.ImageMetadataWithName imageMetadataWithName = imageService.HandleNullableImageRequest(_token, _request.getImage());
        Answer answer = Answer.builder()
                .description(_request.getContent())
                .image(imageMetadataWithName.getImageMetadata())
                .is_right(_request.getIs_right())
                .question(question)
                .build();
        answerRepo.save(answer);
        return AnswerCreateResponse.builder()
                .id(answer.getId())
                .content(answer.getDescription())
                .image(imageMetadataWithName.getImageName())
                .is_right(answer.is_right())
                .build();
    }
}
