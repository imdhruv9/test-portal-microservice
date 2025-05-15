package com.microservice.exam_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDto {
    @NotNull
    private Long userId;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotNull(message = "Topic ID is required")
    private Long topicId;

    @NotNull(message = "Options list is required")
    @Size(min = 4, max = 4, message = "Each question must have exactly 4 options")
    private List<OptionDto> options;


}

