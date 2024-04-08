package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse
{
    private String msg;
    private Integer id;
    private String jwtToken;
    
    public AuthenticationResponse(String msg) {
        this.msg = msg;
    }

    
}
