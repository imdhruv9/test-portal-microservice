package com.microservice.exam_service.service.implementation;

import com.microservice.exam_service.Repository.TopicRepository;
import com.microservice.exam_service.dto.TopicDto;
import com.microservice.exam_service.dto.UserDto;
import com.microservice.exam_service.entity.Topic;
import com.microservice.exam_service.service.TopicService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final RemoteUserService userService;

    public TopicServiceImpl(TopicRepository topicRepository, RemoteUserService userService) {
        this.topicRepository = topicRepository;
        this.userService = userService;
    }

    @Override
    public TopicDto addTopic(TopicDto topicDto){

       UserDto user = userService.getUserById(topicDto.getUserId());


        Topic topic = Topic.builder()
                .name(topicDto.getName())
                .createdBy(user.getId())
                .build();
         Topic savedTopic = topicRepository.save(topic);
         return TopicDto.builder()
                 .name(savedTopic.getName())
                 .userId(savedTopic.getCreatedBy())
                 .build();
    }
}
