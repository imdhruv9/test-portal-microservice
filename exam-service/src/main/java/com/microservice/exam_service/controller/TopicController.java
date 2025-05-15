package com.microservice.exam_service.controller;


import com.microservice.exam_service.dto.TopicDto;
import com.microservice.exam_service.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicDto> addTopic(@RequestBody @Valid TopicDto topicDto){
        TopicDto topic = topicService.addTopic(topicDto);
        return  new ResponseEntity<>(topic, HttpStatus.CREATED);
    }
}
