package com.example.JAQpApi.DTO;


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
    private Integer id;
    private String image;
    private String content;
    private Boolean is_right;
}
