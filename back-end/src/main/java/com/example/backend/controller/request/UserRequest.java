package com.example.backend.controller.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private long idNumber;
    private long phoneNumber;
    private String role;
}
