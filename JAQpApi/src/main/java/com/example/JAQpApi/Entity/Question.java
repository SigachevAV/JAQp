package com.example.JAQpApi.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question
{
    @Id
    @Unique
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_Id;

    @Column
    private String description;

    @OneToOne(optional = true)
    @JoinColumn(name = "imageMetadata_name")
    private ImageMetadata image;

    @ManyToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;
}
