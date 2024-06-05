package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.User.User;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface UserRepo extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
}
