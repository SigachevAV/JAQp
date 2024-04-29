package com.example.JAQpApi.DTO;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizResultRequest
{
    private Integer id;
    private Float result;
}
