package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Quiz.QuizIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface QuizIndexRepo extends ElasticsearchRepository<QuizIndex, Integer>
{
    List<QuizIndex> findAllByName(String name);
}
