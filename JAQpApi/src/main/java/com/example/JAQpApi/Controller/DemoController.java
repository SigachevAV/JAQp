package com.example.JAQpApi.Controller;


import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-controller")
public class DemoController {
    @GetMapping("/test")
    public ResponseEntity<String> sayHello(@Nullable @RequestHeader("Authorization") String bearerToken){
        if ( bearerToken != null ){
            return ResponseEntity.ok("hello, known chelik");
        }
        return ResponseEntity.ok("hello, unknown chelik");
    }


}
