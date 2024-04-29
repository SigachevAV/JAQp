package com.example.JAQpApi.Entity;

import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.common.aliasing.qual.Unique;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_result")
public class UserResult
{
    @Column(nullable = false)
    @Unique
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Float result;

    @OneToOne(optional = true)
    private User user;

    @OneToOne(optional = true)
    private Quiz quiz;
}
