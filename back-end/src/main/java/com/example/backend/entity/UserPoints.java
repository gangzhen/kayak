package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_points")
public class UserPoints {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String idNumber;
    private String region;
    private String level;
    private Integer total;
    private Integer totalCode;
    private String totalLevel;
    private Integer ranking;
    private Integer rankingCode;
    private String rankingLevel;
    private Long points;
    private Date time;
    private Integer year;

    /**
     * 总排名 不入库
     */
    @TableField(exist = false)
    private Integer rank;


    private String sai;
    private String dai;
}
