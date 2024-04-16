package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Quiz.Question;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface QuestionRepo extends JpaRepository<Question, Integer>
{

}
