package com.example.JAQpApi.Entity.Quiz;

import com.example.JAQpApi.Entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz
{
    @Column(nullable = false)
    @Unique
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quiz_id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "imageMetadata_name")
    private ImageMetadata thumnail;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;
}
