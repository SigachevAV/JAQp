package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.UserChangeDataRequest;
import com.example.JAQpApi.DTO.UserGeneralResponse;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement( name = "bearerAuth" )
@Tag( 
    name = "Настройки профиля",
    description = "Изменение различных значение профиля и получение общей информации о пользователе")
@ApiResponses( 
    {
        @ApiResponse(
            description = "UNAUTHENTICATED",
            responseCode = "401",
            content = @Content(
                mediaType = "text/plain"
            )
        ),
        @ApiResponse(
            description = "UNAUTHORIZED",
            responseCode = "403",
            content = @Content(
                mediaType = "text/plain"
            )
        ),
        @ApiResponse(
            description = "User not found",
            responseCode = "404",
            content = @Content(
                mediaType = "text/plain"
            )
        )
    }
)
public class UserController
{
    private final UserService userService;

    @PostMapping("/{id}/setting/first_name")
    @Operation(
        summary = "Задание имени",
        description = "Задание имени пользователю, с заданным id. Требуется совпадение авторизационных данных или наличие прав Администратора",
        parameters = {
            @Parameter(
                description = "JWT auth token",
                name = "Authorization",
                in = ParameterIn.HEADER,
                required = true
            )
        },
        responses = @ApiResponse(
            description = "Изменение прошло успешно",
            responseCode = "200",
            content = @Content(
                mediaType = "text/plain"
            )
        )
    )
    public ResponseEntity setFirstName(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam String first_name)
    {
        try
        {
            userService.SetFirstName(id, Authorization, first_name);
            return ResponseEntity.ok().body("OK");
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.notFound().build();
        }
        catch (UserAccessDeniedExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Error");
        }
    }
    @Operation(
        summary = "Задание фамилии",
        description = "Задание фамилии пользователю, с заданным id. Требуется совпадение авторизационных данных или наличие прав Администратора",
        parameters = {
            @Parameter(
                description = "JWT auth token",
                name = "Authorization",
                in = ParameterIn.HEADER,
                required = true
            )
        },
        responses = @ApiResponse(
            description = "Изменение прошло успешно",
            responseCode = "200",
            content = @Content(
                mediaType = "text/plain"
            )
        )
    )
    @PostMapping("/{id}/setting/second_name")
    public ResponseEntity setSecondName(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam String second_name)
    {
        try
        {
            userService.SetSecondName(id, Authorization, second_name);
            return ResponseEntity.ok().body("OK");
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.notFound().build();
        }
        catch (UserAccessDeniedExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @Operation(
        summary = "Задание даты рождения",
        description = "Задание даты рождения пользователю, с заданным id. Требуется совпадение авторизационных данных или наличие прав Администратора",
        parameters = {
            @Parameter(
                description = "JWT auth token",
                name = "Authorization",
                in = ParameterIn.HEADER,
                required = true
            )
        },
        responses = @ApiResponse(
            description = "Изменение прошло успешно",
            responseCode = "200",
            content = @Content(
                mediaType = "text/plain"
            )
        )
    )
    @PostMapping("/{id}/setting/birth_date")
    public ResponseEntity setBirthDate(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam OffsetDateTime birth_date)
    {
        try
        {
            userService.SetBirthDate(id, Authorization, birth_date);
            return ResponseEntity.ok().body("OK");
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.notFound().build();
        }
        catch (UserAccessDeniedExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/{id}/setting/general")
    @Operation(
        summary = "Задание значений профиля",
        description = "Задание нескольких значений профиля для пользователя, с заданным id. Требуется совпадение авторизационных данных или наличие прав Администратора",
        parameters = {
            @Parameter(
                description = "JWT auth token",
                name = "Authorization",
                in = ParameterIn.HEADER,
                required = true
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(
                    implementation = UserChangeDataRequest.class
                )
            )
        ),
        responses = @ApiResponse(
            description = "Изменение прошло успешно",
            responseCode = "200",
            content = @Content(
                mediaType = "text/plain"
            )
        )
    )
    public ResponseEntity setGeneralInfo(@PathVariable Integer id, @RequestHeader String Authorization, @RequestBody UserChangeDataRequest request)
    {
        userService.SetGeneralData(id, Authorization, request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Получение информации о пользователе",
        description = "Получение информации о пользователе",
        responses = @ApiResponse(
            responseCode = "200",
            description = "",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    
                    implementation = UserGeneralResponse.class
                )
            )
        )
    )
    public ResponseEntity getUser(@PathVariable Integer id)
    {
        return userService.GetUserGeneralInfo(id);
    }
}
