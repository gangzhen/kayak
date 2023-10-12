package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_points")
public class UserPoints {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String region;
    private String idNumber;
    private String level;
    private Integer ranking;
    private String rankLevel;
    private Long points;
    private Long time;
    private Integer year;
}
