package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.OwnedQuizListResponse;
import com.example.JAQpApi.DTO.QuizCreateRequest;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.ImageInvalidException;
import com.example.JAQpApi.Exeptions.ImageStorageException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController
{

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity CreateQuiz(@ModelAttribute QuizCreateRequest request, @RequestHeader String Authorization)
    {
        try
        {
            return ResponseEntity.ok(quizService.CreateQuiz(Authorization, request));
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.badRequest().body("Unauthorized");
        }
        catch (ImageInvalidException e)
        {
            return ResponseEntity.badRequest().body("Invalid Image");
        }
        catch (ImageStorageException e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("");
        }
    }

    @GetMapping("/get_owned/{id}")
    public ResponseEntity GetOwnedQuiz(@PathVariable Integer id)
    {
        //TODO то же самое что и по токену, но с картинками
        return ResponseEntity.status(501).build();
    }

    @GetMapping("/get_owned")
    public ResponseEntity GetOwnedQuiz(@RequestHeader String Authorization)
    {
        try
        {
            return ResponseEntity.ok( quizService.GetOwnedQuiz(Authorization));
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.status(401).body("Unauth");
        }
    }
}
