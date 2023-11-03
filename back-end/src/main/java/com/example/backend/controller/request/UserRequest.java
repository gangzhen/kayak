package com.example.backend.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private String username;
    private String idNumber;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String role;
}
