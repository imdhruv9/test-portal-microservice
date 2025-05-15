package com.example.auth_service.service;

import com.example.auth_service.dto.*;
import com.example.auth_service.utility.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final PasswordEncoder passwordEncoder;

    private  final TokenService tokenService;
    private  final RemoteUserService remoteUserService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, TokenService jwtService, RemoteUserService remoteUserService) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = jwtService;
        this.remoteUserService = remoteUserService;
    }




    public AuthResponse login (AuthRequest request){
            UserDto user = remoteUserService.getUserByUsername(request.getUsername());

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            String accessToken = tokenService.generateToken(user);
            String refreshToken = tokenService.generateRefreshToken(user);

            return new AuthResponse(accessToken, refreshToken);
    }
    public AuthResponse refresh (RefreshTokenReq refreshToken){
            String token = refreshToken.getRefreshToken();
            String username = tokenService.extractUsername(token);
            UserDto user =remoteUserService.getUserByUsername(username);

            String accessToken = tokenService.generateToken(user);

            return new AuthResponse(accessToken, token);
    }

}

