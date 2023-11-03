package com.example.backend.common.encapsulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearStamp {
    long startStamp;
    long endStamp;
}
