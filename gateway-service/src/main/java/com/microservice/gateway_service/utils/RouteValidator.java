package com.microservice.gateway_service.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteValidator {

    // List of public endpoints that do not require token validation
    public static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/auth/refresh",
            "/users/register",
            "/users/register/admin"

    );

    // Predicate to check if the route is secured or open
    public boolean isSecured(String path) {
        return openApiEndpoints.stream()
                .noneMatch(path::contains); // Skip validation if path matches any public API
    }
}