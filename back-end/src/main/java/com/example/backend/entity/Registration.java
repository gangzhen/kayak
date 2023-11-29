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
@TableName("registration")
public class Registration {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer athleteId;
    private String athleteIdNumber;
    private String athleteName;
    private String level;
    private Integer competitionId;
    private String competition;
    private String region;
    private Integer year;
    private Date time;
    private String progress;
    private String progressDes;
    private String reason;
}
