package com.example.JAQpApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JAQpApi.Entity.Quiz.Answer;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface AnswerRepo extends JpaRepository<Answer, Integer>
{

}
