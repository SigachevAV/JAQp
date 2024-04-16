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
public class QuestionCreateRequest
{
    @Schema( example = "123")
    private Integer quiz_id;
    @Schema( example = "what is love")
    private String content;
    private MultipartFile image;
}
