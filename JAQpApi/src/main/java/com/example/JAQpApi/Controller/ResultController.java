package com.example.JAQpApi.Controller;


import com.example.JAQpApi.DTO.QuizResultRequest;
import com.example.JAQpApi.DTO.UserResultsResponse;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.UserResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/api/result")
public class ResultController
{
    private UserResultService userResultService;

    @GetMapping("/answer/{id}")
    public ResponseEntity MakeAnswer(@RequestHeader @Nullable String Authorization, @PathVariable Integer id) throws NotFoundException
    {
        userResultService.MakeAnswer(id, Authorization);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public ResponseEntity MakeResult(@RequestHeader @Nullable String Authorization, @ModelAttribute QuizResultRequest request) throws NotFoundException
    {
        userResultService.MakeResult(request.getId(), request.getResult(), Authorization);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public UserResultsResponse GetResults(@PathVariable Integer id) throws NotFoundException
    {

        return userResultService.GetResults(id);
    }

}
