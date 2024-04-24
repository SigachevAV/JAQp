package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController
{

    private final QuizService quizService;

    @PostMapping("/create")
    public QuizResponse CreateQuiz(@ModelAttribute QuizCreateRequest request, @RequestHeader String Authorization) throws ImageException, NotFoundException
    {
        return quizService.CreateQuiz(Authorization, request);
    }

    @GetMapping("/get_owned/{id}")
    public ResponseEntity GetOwnedQuiz(@PathVariable Integer id)
    {
        //TODO то же самое что и по токену, но с картинками
        return ResponseEntity.status(501).build();
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> Remove(@RequestHeader String Authorization, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        quizService.DeleteQuiz(Authorization, id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/get_questions/{id}")
    public QuestionsOfQuizResponse GetQuestions(@PathVariable Integer id) throws NotFoundException
    {
        return quizService.GetQuestionsOfQuiz(id);
    }

    @GetMapping("/{id}")
    public QuizResponse GetQuiz(@PathVariable Integer id) throws NotFoundException
    {
        return quizService.GetQuiz(id);
    }

    @GetMapping("/get_owned")
    public OwnedQuizListResponse GetOwnedQuiz(@RequestHeader @Nullable String Authorization) throws NotFoundException
    {
        return quizService.GetOwnedQuiz(Authorization);
    }

    @PutMapping("change/{id}")
    public QuizResponse ChangeQuiz(@RequestHeader String Authorization, @RequestBody QuizCreateRequest request, @PathVariable Integer id) throws AccessDeniedException, ImageException, NotFoundException
    {
        return quizService.ChangeQuiz(Authorization, request, id);
    }

    @PutMapping("change_wo_image/{id}")
    public QuizResponse ChangeQuizWOImage(@RequestHeader String Authorization, @RequestBody QuizChangeRequest request, @PathVariable Integer id) throws AccessDeniedException, NotFoundException
    {
        return quizService.ChangeQuiz(Authorization, request, id);
    }

}
