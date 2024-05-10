package com.example.JAQpApi.Entity.Quiz;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "quiz")
public class QuizIndex
{
    @Id
    private Integer id;

    @Field(type = FieldType.Text, name = "name")
    private String name;
}
