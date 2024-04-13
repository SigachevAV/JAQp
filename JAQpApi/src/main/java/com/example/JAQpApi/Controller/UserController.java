package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.UserChangeDataRequest;
import com.example.JAQpApi.DTO.UserGeneralResponse;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/{id}/setting/general")
    public ResponseEntity<String> setGeneralInfo(@PathVariable Integer id, @RequestHeader String Authorization, @RequestBody UserChangeDataRequest request) throws AccessDeniedException, NotFoundException
    {
        userService.SetGeneralData(id, Authorization, request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public UserGeneralResponse getUser(@PathVariable Integer id) throws NotFoundException
    {
        return userService.GetUserGeneralInfo(id);
    }
}
