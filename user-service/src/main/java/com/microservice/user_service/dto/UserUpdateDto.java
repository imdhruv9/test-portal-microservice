package com.microservice.user_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class
UserUpdateDto {
    private String firstName;

    private String lastName;

    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    private Long id;

}
