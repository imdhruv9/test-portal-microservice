package com.microservice.exam_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastExamsResDto {
    private Long userExamId;
    private String topic;
    private Integer totalQues;
    private Integer correctAns;
    private Integer score;


}
