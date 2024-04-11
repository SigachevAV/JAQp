package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.AuthenticationRequest;
import com.example.JAQpApi.DTO.AuthenticationResponse;
import com.example.JAQpApi.DTO.RegistrationRequest;
import com.example.JAQpApi.Exeptions.UserAlreadyExists;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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
        MDC.put("Username", request.getUsername());
        ResponseEntity<String> resp;
        try {
            logger.info("Registration attempt");
            
            authService.register(request);
            resp = ResponseEntity.ok("User registered");

            MDC.put("response", resp.toString());
            logger.info("User registered successfully.");
        } catch (UserAlreadyExists e) {
            resp = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            logger.warn(resp.toString());

        } catch (Exception e){
            resp = ResponseEntity.badRequest().build();
            MDC.put("response", resp.toString());
            logger.error(e.getMessage());
        }

        MDC.clear();
        return resp;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        MDC.put("Username", request.getUsername());
        ResponseEntity<AuthenticationResponse> resp;
        try{
            logger.info("Authentication attempt");
            AuthenticationResponse authResp = authService.authenticate(request);
            //MDC.put("response", authResp.toString());
            logger.info("User authenticated | id = " + authResp.getId() + " | token = " + authResp.getJwtToken() );
            resp = ResponseEntity.ok(authResp);
        }
        catch(UserNotFoundExeption e){
            resp = new ResponseEntity<>(new AuthenticationResponse("No user with this username"), HttpStatus.BAD_REQUEST);
            logger.warn(resp.toString());
        }
        catch(Exception e){
            logger.error(e.getMessage());
            resp = new ResponseEntity<>(new AuthenticationResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        MDC.clear();
        return resp;
    }

}
