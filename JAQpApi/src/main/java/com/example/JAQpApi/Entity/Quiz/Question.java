package com.example.JAQpApi.Entity.Quiz;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Question {
    
    private String text;
    private List<String> answers;
    private int answerNum;
    
}
