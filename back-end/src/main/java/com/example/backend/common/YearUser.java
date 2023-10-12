package com.example.backend.common;

import com.example.backend.entity.UserPoints;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearUser {
    private List<UserPoints> currentYearUser;
    private List<UserPoints> lastYearUser;
}
