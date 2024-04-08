package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.QuestionCreateRequest;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.NotFoundException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
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
    public ResponseEntity CreateQuestion(@RequestHeader String Authorization, @ModelAttribute QuestionCreateRequest request)
    {
        try
        {
            return ResponseEntity.ok(questionService.AddQuestion(Authorization, request));
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.status(403).body("Accesses denied");
        }
        catch (ImageException e)
        {
            return ResponseEntity.internalServerError().build();
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }
    
}
