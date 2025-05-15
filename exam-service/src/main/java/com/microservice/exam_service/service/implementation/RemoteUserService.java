package com.microservice.exam_service.service.implementation;

import com.microservice.exam_service.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteUserService {

     private final RestTemplate restTemplate;

    public RemoteUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public UserDto getUserByUsername(String username) {
//        return restTemplate.getForObject(
//                "http://localhost:8081/users/by-username?username=" + username, UserDto.class);
//    }
    public UserDto getUserById(Long userId) {
        return restTemplate.getForObject("http://localhost:8081/users/" + userId, UserDto.class);
    }


}
