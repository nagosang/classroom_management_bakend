package com.classRoomManagement.cm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Course {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "courseId")
    private String courseId;

    @TableField(value = "courseName")
    private String courseName;
}
