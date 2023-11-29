package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("competition")
public class Competition {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String competition;
    private String level;
    private String region;
    private Integer year;
    private Date time;
}
