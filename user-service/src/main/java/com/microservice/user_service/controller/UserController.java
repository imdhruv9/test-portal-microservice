package com.microservice.user_service.controller;

import com.microservice.user_service.dto.*;
import com.microservice.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserById(@PathVariable @Min(1) Long id){
        UserProfileDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody @Valid UserUpdateDto userUpdateDto){
        UserProfileDto user = userService.updateUserProfile(userUpdateDto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Min(1) Long id){
        userService.deleteUserById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordUpdateDto passwordUpdateDto){
        userService.updatePassword(passwordUpdateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/register/admin")
    public ResponseEntity<UserResponseDto> registerAdmin(@Valid @RequestBody UserRegisterDto userRequestDto){
        UserResponseDto userResponseDto = userService.registerAdmin(userRequestDto);
        return  new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userRequestDto){
        UserResponseDto savedUser = userService.registerUser(userRequestDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}

