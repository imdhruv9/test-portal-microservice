package com.microservice.user_service.service.implementations;

import com.microservice.user_service.dto.*;
import com.microservice.user_service.entity.User;
import com.microservice.user_service.enums.Role;
import com.microservice.user_service.exception.custom.DuplicateEntryException;
import com.microservice.user_service.exception.custom.InvalidCredentialException;
import com.microservice.user_service.exception.custom.UserNotFoundException;
import com.microservice.user_service.mapper.UserMapper;
import com.microservice.user_service.repository.UserRepository;
import com.microservice.user_service.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRegisterDto userRequestDto){
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new DuplicateEntryException("User already exist");
        }
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        return UserResponseDto.builder()
                .email(savedUser.getEmail())
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .build();

    }
    @Override
    public UserResponseDto registerAdmin(UserRegisterDto userRequestDto){
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new DuplicateEntryException("User already exist");
        }
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(encodedPassword)
                .role(Role.ADMIN)
                .build();
        User savedUser = userRepository.save(user);
        return UserResponseDto.builder()
                .email(savedUser.getEmail())
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .build();

    }

    @Override
    public UserProfileDto getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        User user =
                userOptional.orElseThrow(()-> new UserNotFoundException(String.format("No user found with id: %d",id)));
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getFirstName()+" "+user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedDate())
                .build();
    }

    @Override
    public UserProfileDto updateUserProfile(UserUpdateDto dto){
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        User user = optionalUser.orElseThrow(()-> new UserNotFoundException("No user found with this id"));

        // updating user fields from mapper
        UserMapper.updateUserFromDto(dto,user);

        User savedUser = userRepository.save(user);

        return UserProfileDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getFirstName()+" "+savedUser.getLastName())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedDate())
                .build();

    }
    @Override
    public void deleteUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(String.format("No user found with id: %d",id));
        }
        userRepository.deleteById(id);
    }
    @Override
    public void updatePassword(PasswordUpdateDto passwordUpdateDto){
        Optional<User> optionalUser = userRepository.findByUsername(passwordUpdateDto.getUsername());
        User user = optionalUser.orElseThrow(()-> new UserNotFoundException("No user exist with this username"));

        if(!(passwordEncoder.matches(passwordUpdateDto.getOldPassword(),user.getPassword()))){
            throw new InvalidCredentialException("Please enter valid password");
        }

        String encodedNewPassword = passwordEncoder.encode(passwordUpdateDto.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }
    @Override
    public CredResponseDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("No user found with this username"));
        String role = user.getRole().toString();
        return CredResponseDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(role)
                .build();
    }
}
