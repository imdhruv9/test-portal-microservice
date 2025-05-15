package com.microservice.exam_service.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequestDto {

    @NotNull
    private Long topicId;
    @NotNull
    private Integer noOfQuestion;
    @NotNull
    private Long userId;
}
