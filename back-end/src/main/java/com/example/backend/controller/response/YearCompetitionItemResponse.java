package com.example.backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearCompetitionItemResponse {
    private Integer id;
    private String competition;
    private String level;
    private String region;
    private Integer year;
}
