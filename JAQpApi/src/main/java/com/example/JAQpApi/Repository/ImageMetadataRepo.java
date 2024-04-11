package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.ImageMetadata;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface ImageMetadataRepo extends JpaRepository<ImageMetadata, String>
{
    @Override
    Optional<ImageMetadata> findById(String s);
}
