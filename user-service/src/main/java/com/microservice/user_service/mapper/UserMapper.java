package com.microservice.user_service.mapper;
import com.microservice.user_service.dto.UserUpdateDto;
import com.microservice.user_service.entity.User;
import io.micrometer.common.util.StringUtils;

public class UserMapper {


    public static void updateUserFromDto(UserUpdateDto dto, User user) {
        if (StringUtils.isNotBlank(dto.getFirstName())) user.setFirstName(dto.getFirstName());
        if (StringUtils.isNotBlank(dto.getLastName())) user.setLastName(dto.getLastName());
        if (StringUtils.isNotBlank(dto.getUsername())) user.setUsername(dto.getUsername());
        if (StringUtils.isNotBlank(dto.getEmail())) user.setEmail(dto.getEmail());
    }
}

