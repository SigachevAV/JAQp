package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageMetadataRepo extends JpaRepository<ImageMetadata, String>
{
    @Override
    Optional<ImageMetadata> findById(String s);
}
