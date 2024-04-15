package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.ImageUploadResponse;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;


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
@SecurityRequirement( name = "bearerAuth" )
public class ImageController
{
    private final ImageService imageService;

    @GetMapping("/{filename}")
    @Operation(
        description = "Публичный доступ?",
        summary = "Чтение изображения",
        parameters = @Parameter(
            in = ParameterIn.PATH,
            description = "URI изображения"
        ),
        responses = {
            @ApiResponse(
                description = "Запрошенное изображение",
                responseCode = "200",
                content = @Content(
                    mediaType = "multipart/form-data"
                )
            ),
            @ApiResponse(
                description = "Internal Server Error",
                responseCode = "500",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        value = "Unexpected error."
                    )
                )
            ),
            @ApiResponse(
                description = "Invalid format",
                responseCode = "400",
                content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                        value = "Неверный формат изображения."
                    )
                )
            )
        }
    )
    ResponseEntity GetImage(@PathVariable String filename)
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
    @Operation(
        description = "Публичный доступ?",
        summary = "Запись изображения",
        parameters = {
            @Parameter(
                name = "Authorization",
                in = ParameterIn.HEADER,
                description = "JWT auth token"
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Image",
            content = @Content(
                mediaType = "image/*",
                schema = @Schema(
                    implementation = ImageUploadRequest.class
                )
            )
        ),
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
                responseCode = "500",
                content = @Content(
                mediaType = "text/plain"
                )
            ),
            @ApiResponse(
                description = "Неверный файл",
                responseCode = "400",
                content = @Content(
                mediaType = "text/plain"
                )
            ),
            @ApiResponse(
                description = "UNAUTHENTICATED",
                responseCode = "401",
                content = @Content(
                    mediaType = "text/plain"
                )
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
