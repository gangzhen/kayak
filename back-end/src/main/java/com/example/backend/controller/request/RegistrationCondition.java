package com.example.backend.controller.request;

import lombok.Data;

@Data
public class RegistrationCondition {
    private Integer year;
    private String region;
    private String review;
    private int page;
    private int pageSize;
}
