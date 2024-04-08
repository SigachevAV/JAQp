package com.example.JAQpApi.Controller;

import com.example.JAQpApi.DTO.UserChangeDataRequest;
import com.example.JAQpApi.DTO.UserGeneralResponse;
import com.example.JAQpApi.Exeptions.UserAccessDeniedExeption;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Repository.UserRepo;
import com.example.JAQpApi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController
{
    @Autowired
    private UserRepo usersRepo;

    private final UserService userService;

    @PostMapping("/{id}/setting/general")
    public ResponseEntity setGeneralInfo(@PathVariable Integer id, @RequestHeader String Authorization, @RequestBody UserChangeDataRequest request)
    {
        try
        {
            userService.SetGeneralData(id, Authorization, request);
            return ResponseEntity.ok("ok");
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.notFound().build();
        }
        catch (UserAccessDeniedExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Integer id)
    {
        try
        {
            UserGeneralResponse user = userService.GetUserGeneralInfo(id);
            return ResponseEntity.ok().body(user);
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
