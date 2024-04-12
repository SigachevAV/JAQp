package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreateResponse
{
    private Integer quizId;
    private String description;
    private String imageName;
    private String name;
}