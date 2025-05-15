package com.microservice.user_service.service;

import com.microservice.user_service.dto.*;

public interface UserService {

    UserResponseDto registerUser(UserRegisterDto userRequestDto);

    UserResponseDto registerAdmin(UserRegisterDto userRequestDto);
    UserProfileDto getUserById(Long id);
    UserProfileDto updateUserProfile(UserUpdateDto userupdatedto);

    void deleteUserById(Long id);

    void updatePassword(PasswordUpdateDto passwordUpdateDto);

    CredResponseDto getUserByUsername(String username);
}
