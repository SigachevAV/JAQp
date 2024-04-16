package com.example.JAQpApi.Entity.Quiz;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer
{
    @Column
    private String description;

    @OneToOne(optional = true)
    @JoinColumn(name = "imageMetadata_name")
    private ImageMetadata image;

    @Id
    @Unique
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Question question;

    @Column
    private boolean is_right;
}


