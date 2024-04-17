package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.QuestionCreateRequest;
import com.example.JAQpApi.DTO.QuestionCreateResponse;
import com.example.JAQpApi.DTO.QuestionsCreateRequest;
import com.example.JAQpApi.DTO.QuestionsCreateResponse;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    description = "Unexpected Error",
    content = @Content(
        mediaType = "text/plain"
    )
)
@ApiResponse(
    responseCode = "404",
    description = "NOT FOUND",
    content = @Content(
        mediaType = "text/plain"
    )
)
@ApiResponse(
    responseCode = "403",
    description = "UNATHORIZED",
    content = @Content(
        mediaType = "text/plain"
    )
)
@ApiResponse(
    responseCode = "401",
    description = "UNAUTHENTICATED",
    content = @Content(
        mediaType = "text/plain"
    )
    
)
public class QuestionController
{
    private QuestionService questionService;

    @PostMapping("/create")
    @Operation(
        summary = "Добавление вопроса",
        description = "Добавить вопрос к указанному квизу, принадлежащему пользователю. Требуется авторизация или права администратора.",
        responses = @ApiResponse(
            responseCode = "200",
            description = "Вопрос добавлен"
        )
    )
    public ResponseEntity<QuestionCreateResponse> CreateQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
            return new ResponseEntity<QuestionCreateResponse>(questionService.AddQuestion(Authorization, request), HttpStatus.OK);
    }
    
    @PutMapping("/update")
    @Operation(
        summary = "Изменение вопроса",
        description = "Изменить вопрос к указанному квизу, принадлежащему пользователю. Требуется авторизация или права администратора.",
        responses = @ApiResponse(
            responseCode = "200",
            description = "Вопрос изменен"
        )
    )
    public ResponseEntity<QuestionsCreateResponse> UpdateQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionsCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(
        summary = "Получение вопроса",
        description = "Получит вопрос к указанному квизу, принадлежащему пользователю. Требуется авторизация или права администратора.",
        responses = @ApiResponse(
            responseCode = "200",
            description = "Вопрос получен"
        )
    )
    public ResponseEntity<QuestionsCreateResponse> ReadQuestion(
        @RequestHeader String Authorization,
        @PathVariable Integer questionID,
        @PathVariable Integer quizID ) throws AccessDeniedException, ImageException, NotFoundException
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(
        summary = "Получение вопроса",
        description = "Получит вопрос к указанному квизу, принадлежащему пользователю. Требуется авторизация или права администратора.",
        responses = @ApiResponse(
            responseCode = "200",
            description = "Вопрос получен"
        )
    )
    public ResponseEntity<String> DeleteQuestion(
        @RequestHeader String Authorization,
        @PathVariable Integer questionID,
        @PathVariable Integer quizID ) throws AccessDeniedException, ImageException, NotFoundException
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
