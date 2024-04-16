package com.example.JAQpApi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema( example = "123")
    private Integer quizId;
    @Schema( example = "quiz about smth")
    private String description;
    @Schema ( example = "image_3228.png")
    private String imageName;
    @Schema( example = "my quiz 123")
    private String name;
}
