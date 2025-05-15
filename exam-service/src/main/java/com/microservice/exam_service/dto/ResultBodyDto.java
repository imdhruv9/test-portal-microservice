package com.microservice.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultBodyDto {
    private String question;
    private String submittedAns;
    private String correctAns;
    private String status;
}
