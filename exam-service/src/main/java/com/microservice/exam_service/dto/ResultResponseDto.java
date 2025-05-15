package com.microservice.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResultResponseDto {
    private String topic;
    private Integer totalQuestion;
    private Integer totalCorrect;
    private Integer score;
    private List<ResultBodyDto> body;
}
