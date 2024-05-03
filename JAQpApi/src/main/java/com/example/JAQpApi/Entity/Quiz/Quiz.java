package com.example.JAQpApi.Entity.Quiz;

import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Entity.UserResult;
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
    private Integer id;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "imageMetadata_name", nullable = true)
    private ImageMetadata thumbnail;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    @OneToMany
    private List<Tag> tags;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<UserResult> userResults;
}
