package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.AnswerCreateRequest;
import com.example.JAQpApi.DTO.ChangeAnswerRequest;
import com.example.JAQpApi.DTO.GetAnswerResponse;
import com.example.JAQpApi.Entity.Answer;
import com.example.JAQpApi.Entity.ImageMetadata;
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

    private GetAnswerResponse GetAnswerResponseFactory(Answer _answer)
    {
        return GetAnswerResponse.builder()
                .isRight(_answer.is_right())
                .content(_answer.getDescription())
                .image((_answer.getImage() != null) ? _answer.getImage().getName() : null)
                .id(_answer.getId())
                .build();
    }

    public GetAnswerResponse AddAnswer(String _token, AnswerCreateRequest _request) throws NotFoundException, AccessDeniedException, ImageException
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
        return GetAnswerResponseFactory(answer);
    }

    public void DeleteAnswer(String _token, Integer _id) throws NotFoundException, AccessDeniedException, ImageException
    {
        Answer answer = answerRepo.findById(_id).orElseThrow(() -> new NotFoundException("Answer", "id", _id.toString()));
        Question question = questionService.ValidateAccessAndGetQuestion(_token, answer.getQuestion().getQuestion_Id()).orElseThrow(() -> new NotFoundException(("")));
        ImageMetadata image = answer.getImage();
        answerRepo.delete(answer);
        imageService.DeleteImage(image, _token);
    }

    public GetAnswerResponse GetAnswer(Integer _id) throws NotFoundException
    {
        Answer answer = answerRepo.findById(_id).orElseThrow(() -> new NotFoundException("Answer", "id", _id.toString()));
        return GetAnswerResponseFactory(answer);
    }

    public GetAnswerResponse ChangeAnswer(String _token, Integer _id, AnswerCreateRequest _request) throws AccessDeniedException, NotFoundException, ImageException
    {
        Answer answer = answerRepo.findById(_id).orElseThrow(() -> new NotFoundException("Answer", "id", _id.toString()));
        Question question = questionService.ValidateAccessAndGetQuestion(_token, answer.getQuestion().getQuestion_Id()).orElseThrow(() -> new NotFoundException(("")));
        ImageMetadata imageMetadata = answer.getImage();
        answer.setImage(null);
        answer.set_right(_request.getIs_right());
        answer.setDescription(_request.getContent());
        answerRepo.save(answer);
        imageMetadata = imageService.ChangeImage(imageMetadata, _token, _request.getImage());
        answer.setImage(imageMetadata);
        answerRepo.save(answer);
        return GetAnswerResponseFactory(answer);
    }

    public GetAnswerResponse ChangeAnswer(String _token, Integer _id, ChangeAnswerRequest _request) throws AccessDeniedException, NotFoundException
    {
        Answer answer = answerRepo.findById(_id).orElseThrow(() -> new NotFoundException("Answer", "id", _id.toString()));
        Question question = questionService.ValidateAccessAndGetQuestion(_token, answer.getQuestion().getQuestion_Id()).orElseThrow(() -> new NotFoundException(("")));
        answer.set_right(_request.getIs_right());
        answer.setDescription(_request.getContent());
        answerRepo.save(answer);
        return GetAnswerResponseFactory(answer);
    }

}
