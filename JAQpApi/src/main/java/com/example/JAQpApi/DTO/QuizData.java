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
public class QuizData
{
    @Schema(example = "123")
    private Integer id;
    @Schema(example = "my quiz 123")
    private String name;
}
