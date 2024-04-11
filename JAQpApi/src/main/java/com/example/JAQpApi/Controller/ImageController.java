package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.ImageUploadResponse;
import com.example.JAQpApi.Exeptions.ImageException;
import com.example.JAQpApi.Exeptions.ImageInvalidException;
import com.example.JAQpApi.Exeptions.ImageStorageException;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/image")
@Tag(
    name = "Image related endpoints",
    description = "Endpoint-ы, ответственные за чтение и запись изображений"
)
public class ImageController
{
    private final ImageService imageService;

    static final Logger logger =
            LoggerFactory.getLogger(ImageController.class);

    @GetMapping("/{filename}")
    @Operation(
        description = "Публичный доступ?",
        summary = "Чтение изображения",
        requestBody = @RequestBody(
            required = true,
            description = "URI изображения"
        ),
        responses = {
            @ApiResponse(
                description = "Запрошенное изображение",
                responseCode = "200",
                content = @Content(
                    mediaType = "image/*"
                )
            ),
            @ApiResponse(
                description = "Internal Server Error",
                responseCode = "500"
            ),
            @ApiResponse(
                description = "Invalid format",
                responseCode = "400"
            ),
            @ApiResponse(
                description = "Unexpected Error",
                responseCode = "400"
            )
        }
    )
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
    @Operation(
        description = "Публичный доступ?",
        summary = "Запись изображения",
        parameters = {
            @Parameter(
                
            )
        },
        responses = {
            @ApiResponse(
                description = "Изображение записано",
                responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        implementation = ImageUploadResponse.class
                    )
                )
            ),
            @ApiResponse(
                description = "Internal Server Error",
                responseCode = "500"
            ),
            @ApiResponse(
                description = "Неверный файл",
                responseCode = "400"
            ),
            @ApiResponse(
                description = "Unexpected Error",
                responseCode = "400"
            ),
            @ApiResponse(
                description = "User Not Found",
                responseCode = "400"
            )
            
        }
    )
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
            logger.debug("Unexpected error");
            logger.debug(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
