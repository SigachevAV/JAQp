package com.example.JAQpApi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserStatsResponse {

    @Schema(
        example = "123"
    )
    private int quizNum;
    @Schema(
        example = "12.34"
    )
    private float averageScore;

    @Schema(
        example = "12"
    )
    private int quizNumLastWeek;

    @Schema(
        example = "1.23"
    )
    private int quizAverageLastWeek;

}
