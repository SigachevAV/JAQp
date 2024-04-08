package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer>
{

}
