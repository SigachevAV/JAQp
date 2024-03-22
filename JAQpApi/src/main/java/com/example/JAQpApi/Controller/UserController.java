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

    @PostMapping("/{id}/setting/first_name")
    public ResponseEntity setFirstName(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam String first_name)
    {
        try
        {
            userService.SetFirstName(id, Authorization, first_name);
            return ResponseEntity.ok().body("OK");
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

    @PostMapping("/{id}/setting/second_name")
    public ResponseEntity setSecondName(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam String second_name)
    {
        try
        {
            userService.SetSecondName(id, Authorization, second_name);
            return ResponseEntity.ok().body("OK");
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


    @PostMapping("/{id}/setting/last_name")
    public ResponseEntity setLastName(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam String last_name)
    {
        try
        {
            userService.SetLastName(id, Authorization, last_name);
            return ResponseEntity.ok().body("OK");
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

    @PostMapping("/{id}/setting/birth_date")
    public ResponseEntity setBirthDate(@PathVariable Integer id, @RequestHeader String Authorization, @RequestParam OffsetDateTime birth_date)
    {
        try
        {
            userService.SetBirthDate(id, Authorization, birth_date);
            return ResponseEntity.ok().body("OK");
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
