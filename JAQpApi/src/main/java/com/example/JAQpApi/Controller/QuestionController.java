package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.ImageException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/question")
public class QuestionController
{
    private QuestionService questionService;

    @PostMapping("/add")
    public QuestionCreateResponse CreateQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
            return questionService.AddQuestion(Authorization, request);
    }

    @GetMapping("/{id}")
    public GetQuestionResponse getQuestion(@PathVariable Integer id) throws NotFoundException
    {
        return questionService.GetQuestion(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity Remove(@RequestHeader String Authorization, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        questionService.DeleteQuestion(Authorization, id);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("change/{id}")
    public GetQuestionResponse ChangeQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionCreateRequest request, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        return questionService.ChangeQuestion(Authorization, id, request);
    }

    @PutMapping("change_wo_image/{id}")
    public GetQuestionResponse ChangeQuestionWOImage(@RequestHeader String Authorization, @RequestParam String description, @PathVariable Integer id) throws AccessDeniedException, NotFoundException
    {
        return questionService.ChangeQuestion(Authorization, id, description);
    }
}
