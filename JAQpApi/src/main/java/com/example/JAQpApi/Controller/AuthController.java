package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.AuthenticationRequest;
import com.example.JAQpApi.DTO.AuthenticationResponse;
import com.example.JAQpApi.DTO.RegistrationRequest;
import com.example.JAQpApi.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegistrationRequest request
    ) {
        logger.info("Registration attempt | Username = " + request.getUsername() );
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        logger.info("Authentication attempt");
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
