package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.User.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
}
