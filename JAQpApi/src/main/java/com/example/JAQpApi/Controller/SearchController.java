package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.OwnedQuizListResponse;
import com.example.JAQpApi.DTO.SearchRequest;
import com.example.JAQpApi.Service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchController
{
    private final SearchService searchService;

    @GetMapping("/find")
    public OwnedQuizListResponse FindQuiz(SearchRequest request)
    {
        return searchService.FindQuiz(request);
    }
}
