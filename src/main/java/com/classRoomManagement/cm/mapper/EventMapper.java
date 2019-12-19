package com.classRoomManagement.cm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classRoomManagement.cm.entity.Event;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface EventMapper extends BaseMapper<Event> {
    @Select("SELECT `event`.id,`event`.`name`,`event`.`comment`,`event`.proposerId,`event`.proposerName,`event`.proposerDay,`event`.`day`,`event`.time,classroom.roomId, `event`.endTime, `event`.situation FROM `event` LEFT JOIN classroom ON `event`.roomId = classroom.id ORDER BY id DESC LIMIT #{limitLow},#{limitTop}")
    List<HashMap<String, Object>> selectByPage(@Param("limitLow") int limitLow, @Param("limitTop") int limitTop);
}
