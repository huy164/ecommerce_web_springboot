package com.backend.lavender.models.modelsDto;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterDto {
    public String username;
    public String name;
    public String phoneNumber;
    public String email;
    public Date dob;
    public String password;
}
