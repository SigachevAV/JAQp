package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/answer")
public class AnswerController
{
    private final AnswerService answerService;

    @PostMapping("/add")
    public GetAnswerResponse AddAnswer(@RequestHeader String Authorization, @ModelAttribute AnswerCreateRequest request) throws AccessDeniedException, ImageException, NotFoundException
    {
        return answerService.AddAnswer(Authorization, request);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> Remove(@RequestHeader String Authorization, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        answerService.DeleteAnswer(Authorization, id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{id}")
    public GetAnswerResponse GetAnswer(@PathVariable Integer id) throws NotFoundException
    {
        return answerService.GetAnswer(id);
    }

    @PutMapping("change/{id}")
    public GetAnswerResponse ChangeAnswer(@RequestHeader String Authorization, @RequestBody AnswerCreateRequest request, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        return answerService.ChangeAnswer(Authorization, id,  request);
    }

    @PutMapping("change_wo_image/{id}")
    public GetAnswerResponse ChangeQuizWOImage(@RequestHeader String Authorization, @RequestBody ChangeAnswerRequest request, @PathVariable Integer id) throws AccessDeniedException, NotFoundException
    {
        return answerService.ChangeAnswer(Authorization, id, request);
    }

}
