package com.example.JAQpApi.Entity;

import com.example.JAQpApi.Entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

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
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "imageMetadata_name")
    private ImageMetadata thumnail;
}
