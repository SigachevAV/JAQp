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
public class AnswerCreateResponse
{
    @Schema(example = "12")
    private Integer question_id;
    @Schema(example = "answer#123")
    private String content;
    
    private String image;
    @Schema(example = "true")
    private Boolean is_right;
}
