package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.ImageUploadResponse;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.ImageInvalidException;
import com.example.JAQpApi.Exeptions.ImageStorageException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
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
    ResponseEntity GetImage(@PathVariable String filename)
    {
        try
        {
            byte[] file = imageService.LoadImage(filename);
            return ResponseEntity.ok().contentType(ImageService.GetType(filename)).body(file);
        }
        catch (ImageStorageException e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        catch (ImageInvalidException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/upload")
    ResponseEntity UploadImage(@ModelAttribute ImageUploadRequest file, @RequestHeader String Authorization)
    {
        try
        {
            ImageUploadResponse response =  new ImageUploadResponse(imageService.UploadFile(file.getFile(), Authorization));
            return ResponseEntity.ok().body(response);
        }
        catch (ImageInvalidException e)
        {
            return ResponseEntity.badRequest().body(e);
        }
        catch (ImageException e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
