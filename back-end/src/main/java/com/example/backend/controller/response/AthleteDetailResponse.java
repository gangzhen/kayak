package com.example.backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteDetailResponse {
    private Integer id;
    private String name;
    private String idNumber;
}
