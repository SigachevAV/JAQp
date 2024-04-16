package com.example.JAQpApi.DTO;

import com.example.JAQpApi.Entity.User.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @Schema( example = "username123")
    private String username;
    @Schema( example = "password123")
    private String password;
    @Schema( example = "USER")
    private Role role;
}
