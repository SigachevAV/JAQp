package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.ImageUploadResponse;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.ImageService;
import lombok.AllArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController
{
    private final ImageService imageService;

    @GetMapping("/{filename}")
    ResponseEntity GetImage(@PathVariable String filename) throws ImageStorageException, ImageInvalidException
    {
        byte[] file = imageService.LoadImage(filename);
        return ResponseEntity.ok().contentType(ImageService.GetType(filename)).body(file);
    }

    @DeleteMapping("/{filename}")
    ResponseEntity<String> DeleteImage(@PathVariable String filename, @RequestHeader String Authorization) throws AccessDeniedException, ImageException, NotFoundException
    {
        imageService.DeleteImage(filename, Authorization);
        return ResponseEntity.ok("Image with name " + filename + " deleted");
    }

    @PostMapping("/upload")
    ImageUploadResponse UploadImage(@ModelAttribute ImageUploadRequest file, @RequestHeader String Authorization) throws ImageException, NotFoundException
    {
        return new ImageUploadResponse(imageService.UploadFile(file.getFile(), Authorization));
    }
}
