package com.microservice.exam_service.service;

import com.microservice.exam_service.dto.QuestionListDto;
import com.microservice.exam_service.dto.QuestionRequestDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface QuestionService {
    void addQuestion(@Valid QuestionRequestDto questionRequestDto);

    void addMultipleQuestion(QuestionListDto questionListDto);

    void addQuestionFromCsv(MultipartFile file) throws IOException;

    @Transactional
    void addQuestionFromExcel(MultipartFile file)throws IOException;
}
