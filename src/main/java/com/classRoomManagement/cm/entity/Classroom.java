package com.classRoomManagement.cm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
@TableName("classroom")
public class Classroom {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "buildId")
    private int buildId;

    @TableField(value = "roomId")
    private String roomId;

    @TableField(value = "floor")
    private int floor;

    @TableField(value = "equipment")
    private String equipment;
}
