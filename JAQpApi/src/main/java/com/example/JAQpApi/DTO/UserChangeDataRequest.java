package com.example.JAQpApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserChangeDataRequest
{
    private String firstName;
    private String lastName;
    private String secondName;
    private OffsetDateTime birthDate;
}
