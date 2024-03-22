package com.example.JAQpApi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAQpApi.DTO.QuizResponse;

@RestController
@RequestMapping("/api/quiz/")
public class QuizController {
    
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable Integer id){

        return ResponseEntity.ok(new QuizResponse());

    }

    @PostMapping("")
    public ResponseEntity postQuiz(){

        return ResponseEntity.ok("ok");
    }
    
}
