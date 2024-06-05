package com.example.JAQpApi.Controller;


import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.AuthService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/demo-controller")
@Hidden
@SecurityRequirement( name = "bearerAuth" )
public class DemoController {

    private final AuthService authService;


    @GetMapping("/test")
    public ResponseEntity<String> sayHello(@Nullable @RequestHeader("Authorization") String authorization) throws NotFoundException{
        
        if ( authorization != null ){
            User user = authService.GetUserByToken(authorization);
            return ResponseEntity.ok("hello, " + user.getUsername());
        }
        return ResponseEntity.ok("hello, unknown chelik");
    }

}
