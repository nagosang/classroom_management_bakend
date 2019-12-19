package com.classRoomManagement.cm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "admin")
    private String admin;

    @TableField(value = "password")
    private String password;
}
