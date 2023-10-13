package com.example.backend.controller.request;

import lombok.Data;

@Data
public class Condition {
    private String name;
    private String region;
    private String sort;
    private int age;
    private int year;
    private int page;
    private int pageSize;
}
