package com.classRoomManagement.cm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("event")
public class Event {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "proposerId")
    private String proposerId;

    @TableField(value = "proposerName")
    private String proposerName;

    @TableField(value = "proposerDay")
    private String proposerDay;

    @TableField(value = "day")
    private String day;

    @TableField(value = "time")
    private String time;

    @TableField(value = "roomId")
    private int roomId;

    @TableField(value = "endTime")
    private String endTIme;

    @TableField(value = "situation")
    private int situation;
}
