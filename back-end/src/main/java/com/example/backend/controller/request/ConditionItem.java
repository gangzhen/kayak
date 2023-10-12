package com.example.backend.controller.request;

import lombok.Data;

@Data
public class ConditionItem {
    private String name;
    private int page;
    private int pageSize;
}
