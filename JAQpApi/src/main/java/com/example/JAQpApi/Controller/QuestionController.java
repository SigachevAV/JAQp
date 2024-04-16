package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.QuestionCreateRequest;
import com.example.JAQpApi.DTO.QuestionCreateResponse;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.QuestionService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/question")
@Tag(
    name = "Вопросы"
)
@ApiResponse(
    responseCode = "500",
    description = "Unexpected Error"
)
@ApiResponse(
    responseCode = "404",
    description = "NOT FOUND"
)
@ApiResponse(
    responseCode = "403",
    description = "UNATHORIZED"
)
@ApiResponse(
    responseCode = "401",
    description = "UNAUTHENTICATED"
)
public class QuestionController
{
    private QuestionService questionService;

    @PostMapping("/add")
    public QuestionCreateResponse CreateQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
            return questionService.AddQuestion(Authorization, request);
    }
    
}
