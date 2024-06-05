package com.example.JAQpApi.Repository;


import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JAQpApi.Entity.Quiz.ImageMetadata;

import java.util.Optional;

@Hidden
public interface ImageMetadataRepo extends JpaRepository<ImageMetadata, String>
{
    @Override
    Optional<ImageMetadata> findById(String s);
}
