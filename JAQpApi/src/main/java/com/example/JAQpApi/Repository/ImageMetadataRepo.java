package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageMetadataRepo extends JpaRepository<ImageMetadata, String>
{
    @Override
    Optional<ImageMetadata> findById(String s);
}
