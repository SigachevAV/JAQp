package com.example.JAQpApi.DTO;

import org.checkerframework.checker.index.qual.SearchIndexBottom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class QuizStatsResponse {


    @Schema(
        example = "123"
    )
    private int numberOfTakes;

    @Schema(
        example = "12.3"
    )
    private float averageScore;

}
