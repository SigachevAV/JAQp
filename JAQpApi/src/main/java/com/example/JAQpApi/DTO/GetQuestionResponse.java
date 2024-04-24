package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetQuestionResponse
{
    private Integer id;
    private String description;
    private String image;
    private List<Integer> answers;
}
