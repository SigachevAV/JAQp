package com.example.JAQpApi.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag( 
    name = "Admin endpoint-ы",
    description = "Ednpoint-ы для пользователей с правами доступа Admin"
    )
@SecurityRequirement( name = "bearerAuth" )
@ApiResponses(
    {
        @ApiResponse(
            responseCode = "200",
            description = "admin ok"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "UNAUTHENTICATED"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "UNAUTHORIZED"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Unexpected Server Error"
        )
    }
)
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(
        summary = "ADMIN GET"
    )
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Operation(
        summary = "ADMIN POST"
    )
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Operation(
        summary = "ADMIN PUT"
    )
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Operation(
        summary = "ADMIN DELETE"
    )
    public String delete() {
        return "DELETE:: admin controller";
    }

}