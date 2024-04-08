package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreateRequest
{
    private String name;
    private MultipartFile thumnail;
    private String description;
}
