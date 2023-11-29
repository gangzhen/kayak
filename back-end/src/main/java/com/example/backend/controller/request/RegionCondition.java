package com.example.backend.controller.request;

import lombok.Data;

@Data
public class RegionCondition {
    private Integer year;
    private String region;
    private int page;
    private int pageSize;
}
