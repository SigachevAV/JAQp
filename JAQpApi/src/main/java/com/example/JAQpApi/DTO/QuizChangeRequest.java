package com.example.JAQpApi.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuizChangeRequest
{
    private String name;
    private String description;
}
