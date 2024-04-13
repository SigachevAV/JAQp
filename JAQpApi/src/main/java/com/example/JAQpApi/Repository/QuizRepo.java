package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Quiz;
import com.example.JAQpApi.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Integer>
{
    List<Quiz> findAllByOwner(User owner);
}
