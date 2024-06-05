package com.example.JAQpApi.Entity.Quiz;

import com.example.JAQpApi.Entity.User.User;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Hidden
public class ImageMetadata
{
    @Column
    @Unique
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "thumbnail", optional = true)
    private Quiz quiz;

    @OneToOne(mappedBy = "image", optional = true)
    private Question question;
}
