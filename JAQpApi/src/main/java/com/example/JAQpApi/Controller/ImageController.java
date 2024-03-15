package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.ImageUploadResponse;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.ImageInvalidException;
import com.example.JAQpApi.Exeptions.ImageStorageException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Service.ImageService;
import lombok.AllArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController
{
    private final ImageService imageService;

    static final Logger logger =
            LoggerFactory.getLogger(ImageController.class);

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

            logger.debug("Неверный файл");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ImageException e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        catch (UserNotFoundExeption e)
        {

            logger.debug("Неверный юзер");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            logger.debug("пиздец");
            logger.debug(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
