package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAnswerResponse
{
    private boolean isRight;
    private String content;
    private String image;
    private int id;
}
