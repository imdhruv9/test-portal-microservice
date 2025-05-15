package com.microservice.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitExamResDto {
    private String message;
    private Integer noOfQuestions;
    private Integer correctAns;
    private Integer score;
}
