package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuestionCreateResponse
{
    private Integer id;
    private String content;
    private String imageName;
}
