package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateRequest
{
    @Schema(example = "12")
    private Integer question_id;
    @Schema(example = "answer#123")
    private String content;

    private MultipartFile image;
    
    @Schema(example = "true")
    private Boolean is_right;
}
