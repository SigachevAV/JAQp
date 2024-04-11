package com.example.JAQpApi.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController {



    @PostMapping
    public ResponseEntity<String> post(List<String> questions){
        




        return new ResponseEntity<>(HttpStatus.OK);
    }




    
    
}
