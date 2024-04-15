package com.example.JAQpApi.Controller;


import com.example.JAQpApi.DTO.AnswerCreateRequest;
import com.example.JAQpApi.DTO.AnswerCreateResponse;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/answer")
public class AnswerController
{
    private final AnswerService answerService;

    @PostMapping("/add")
    public AnswerCreateResponse AddAnswer(@RequestHeader String Authorization, @ModelAttribute AnswerCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
        return answerService.AddAnswer(Authorization, request);
    }
}
