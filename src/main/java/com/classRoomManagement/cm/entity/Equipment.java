package com.classRoomManagement.cm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
@TableName
public class Equipment {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "equipment")
    private String equipment;

    @TableField(value = "equipmentComment")
    private String equipmentComment;

    @TableField(value = "unit")
    private String unit;
}
