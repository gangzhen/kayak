package com.example.backend.controller.request;

import lombok.Data;

@Data
public class UserRequest {
    private Integer id;
    private String username;
    private String idNumber;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String role;
}
