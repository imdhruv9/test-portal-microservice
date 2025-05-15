package com.microservice.exam_service.service;

import com.microservice.exam_service.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {
    ExamResponseDto createExam(ExamRequestDto requestDto);

    List<ExamResponseDto> fetchExams();

    ExamStartResDto startExam(Long userID, Long examId);

    SubmitExamResDto submitExam(Long id, SubmitExamReqDto reqDto);

    ResultResponseDto viewResult(Long userExamId);

    @Transactional
    List<PastExamsResDto> viewPastExams(Long userId);

    ReviewResDto reviewAnswers(Long userExamId, SubmitExamReqDto reqDto);
}
