package com.microservice.exam_service.controller;


import com.microservice.exam_service.dto.*;
import com.microservice.exam_service.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<ExamResponseDto> createExam(@RequestBody @Valid ExamRequestDto requestDto){
        ExamResponseDto responseDto = examService.createExam(requestDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<ExamResponseDto>> fetchExams(){
        List<ExamResponseDto> exams = examService.fetchExams();
        return new ResponseEntity<>(exams,HttpStatus.OK);
    }
    @GetMapping("/{userId}/{examId}/start")
    public ResponseEntity<ExamStartResDto> startExam(@PathVariable Long userId, @PathVariable Long examId){
        ExamStartResDto response = examService.startExam(userId,examId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/{userExamId}/submit")
    public ResponseEntity<SubmitExamResDto> submitExam(@PathVariable Long userExamId, @RequestBody SubmitExamReqDto submitExamReqDto){
        SubmitExamResDto resDto = examService.submitExam(userExamId,submitExamReqDto);
        return new ResponseEntity<>(resDto,HttpStatus.OK);
    }
    @GetMapping("/{userExamId}/result")
    public ResponseEntity<ResultResponseDto> viewResult (@PathVariable Long userExamId){
        ResultResponseDto result = examService.viewResult(userExamId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{userId}/past-exams")
    public ResponseEntity<List<PastExamsResDto>> viewPastExams(@PathVariable Long userId){
        List<PastExamsResDto> pastExams = examService.viewPastExams(userId);
        return new ResponseEntity<>(pastExams,HttpStatus.OK);
    }
    @PostMapping("/{userExamId}/review")
    public ResponseEntity<ReviewResDto> reviewAnswers(@PathVariable Long userExamId, @RequestBody SubmitExamReqDto submitExamReqDto){
        ReviewResDto resDto = examService.reviewAnswers(userExamId,submitExamReqDto);
        return new ResponseEntity<>(resDto,HttpStatus.OK);
    }

}



