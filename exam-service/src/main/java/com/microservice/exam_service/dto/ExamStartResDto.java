package com.microservice.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ExamStartResDto {
    private Long userExamId;
    private String topic;
    private List<ExamQuestionResDto> questions;
}
