package com.example.JAQpApi.Controller;


import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/demo-controller")
public class DemoController {

    private final AuthService authService;


    @GetMapping("/test")
    public ResponseEntity<String> sayHello(@Nullable @RequestHeader("Authorization") String authorization) throws UserNotFoundExeption{
        
        if ( authorization != null ){
            User user = authService.GetUserByToken(authorization);
            return ResponseEntity.ok("hello, " + user.getUsername());
        }
        return ResponseEntity.ok("hello, unknown chelik");
    }

}
