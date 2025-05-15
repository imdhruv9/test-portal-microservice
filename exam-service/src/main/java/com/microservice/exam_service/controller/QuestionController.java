package com.microservice.exam_service.controller;

import com.microservice.exam_service.dto.QuestionListDto;
import com.microservice.exam_service.dto.QuestionRequestDto;
import com.microservice.exam_service.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody @Valid QuestionRequestDto questionRequestDto){
         questionService.addQuestion(questionRequestDto);
        return new ResponseEntity<>("Question uploaded successfully", HttpStatus.CREATED);
    }
    @PostMapping("/bulk")
    public ResponseEntity<String> addMultipleQuestion(@RequestBody @Valid QuestionListDto questionListDto){
        questionService.addMultipleQuestion(questionListDto);
        return new ResponseEntity<>("All questions successfully uploaded",HttpStatus.CREATED);
    }
    @PostMapping("/csv")
    public ResponseEntity<String> addQuestionFromCsv(@RequestParam("file") MultipartFile file) throws IOException {
        questionService.addQuestionFromCsv(file);
        return new ResponseEntity<>("Question uploaded Successfully via csv file",HttpStatus.OK);
    }
    @PostMapping("/excel")
    public ResponseEntity<String> addQuestionFromExcel(@RequestParam("file") MultipartFile file) throws  IOException{
        questionService.addQuestionFromExcel(file);
        return new ResponseEntity<>("Question uploaded successfully via excel file",HttpStatus.OK);
    }

}