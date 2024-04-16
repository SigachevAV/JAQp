package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.ImageUploadRequest;
import com.example.JAQpApi.DTO.OwnedQuizListResponse;
import com.example.JAQpApi.DTO.QuizCreateRequest;
import com.example.JAQpApi.DTO.QuizResponse;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.parameters.P;
import com.example.JAQpApi.DTO.*;
import com.example.JAQpApi.Exceptions.*;
import com.example.JAQpApi.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/quiz")
@Tag(name = "Работа с квизами")
@ApiResponses(
    {
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not found"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "UNAUTHORIZED"
        )

    }
)
public class QuizController
{

    private final QuizService quizService;

    @PostMapping("/create")
    @Operation(
        summary = "Создание квиза",
        description = "Создание квиза. Требуется авторизация",
        parameters = @Parameter(
            in = ParameterIn.HEADER,
            description = "JWT auth token",
            required = true
        ),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(
                    implementation = QuizCreateRequest.class
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Квиз создан успешно."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Неправильное изображение."
            )
        }
    )
    public ResponseEntity CreateQuiz(@ModelAttribute QuizCreateRequest request, @RequestHeader String Authorization)
    {
        //try
        //{
        //    return ResponseEntity.ok(quizService.CreateQuiz(Authorization, request));
        //}
        //catch (UserNotFoundExeption e)
        //{
        //    return ResponseEntity.badRequest().body("Unauthorized");
        //}
        //catch (ImageInvalidException e)
        //{
        //    return ResponseEntity.badRequest().body("Invalid Image");
        //}
        //catch (ImageStorageException e)
        //{
        //    return ResponseEntity.internalServerError().body(e.getMessage());
        //}
        //catch (Exception e)
        //{
        //    return ResponseEntity.badRequest().body("");
        //}
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Получить информацию о квизе",
        description = "Получить информацию о квизе с заданным id. При попытке получить скрытый квиз, непренадлежащий авторизированному пользователю - 403",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Квиз получен",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        implementation = QuizResponse.class
                    )
                )
            )
        }

    )
    @GetMapping("/{id}")
    public ResponseEntity GetQuiz(@PathVariable Integer id)
    {
        //try
        //{
        //    return ResponseEntity.ok(quizService.GetQuiz(id));
        //}
        //catch (NotFoundException e)
        //{
        //    return ResponseEntity.notFound().build();
        //}
        return ResponseEntity.notFound().build();
    }


    @Operation(
        summary = "Получить свои квизы.",
        description = "Получить свои квизы. Необходима авторизация.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Квизы получены",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        implementation = OwnedQuizListResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "UNAUTHENTICATED"
            )
        }

    )
    @GetMapping("/get_owned")
    public ResponseEntity GetOwnedQuiz(@RequestHeader @Nullable String Authorization)
    {
        //try
        //{
        //    return ResponseEntity.ok(quizService.GetOwnedQuiz(Authorization));
        //}
        //catch (UserNotFoundExeption e)
        //{
        //    return ResponseEntity.status(401).body("Unauth");
        //}
        return ResponseEntity.status(401).body("Unauth");
    }
}
/*
    public QuizCreateResponse CreateQuiz(@ModelAttribute QuizCreateRequest request, @RequestHeader String Authorization) throws ImageException, NotFoundException
    {
        return quizService.CreateQuiz(Authorization, request);
    }

    @GetMapping("/get_owned/{id}")
    public ResponseEntity GetOwnedQuiz(@PathVariable Integer id)
    {
        //TODO то же самое что и по токену, но с картинками
        return ResponseEntity.status(501).build();
    }

    @GetMapping("/get_questions/{id}")
    public QuestionsOfQuizResponse GetQuestions(@PathVariable Integer id) throws NotFoundException
    {
        return quizService.GetQuestionsOfQuiz(id);
    }

    @GetMapping("/{id}")
    public QuizResponse GetQuiz(@PathVariable Integer id) throws NotFoundException
    {
        return quizService.GetQuiz(id);
    }

    @GetMapping("/get_owned")
    public OwnedQuizListResponse GetOwnedQuiz(@RequestHeader @Nullable String Authorization) throws NotFoundException
    {
        return quizService.GetOwnedQuiz(Authorization);
    }
}

*/