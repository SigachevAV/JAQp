package com.example.JAQpApi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuestionCreateResponse
{
    @Schema( example = "123")
    private Integer id;
    @Schema ( example = "what is love")
    private String content;
    @Schema( example = "img3228")
    private String imageName;
}
