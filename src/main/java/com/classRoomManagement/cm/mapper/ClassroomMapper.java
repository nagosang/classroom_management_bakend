package com.classRoomManagement.cm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classRoomManagement.cm.entity.Classroom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface ClassroomMapper extends BaseMapper<Classroom> {
    @Select("SELECT * FROM classroom LEFT JOIN (SELECT * FROM courseTable WHERE `day` = #{day} AND time LIKE '%${time}%') AS today ON classroom.id = today.roomId WHERE classroom.buildId = #{buildId} LIMIT #{limitLow},#{limitTop}")
    List<HashMap<String, Object>> selectByBuild(@Param("day") int day, @Param("time") int time, @Param("buildId") int buildId, @Param("limitLow") int limitLow, @Param("limitTop") int limitTop);

    @Select("SELECT * FROM classroom LEFT JOIN (SELECT * FROM courseTable WHERE `day` = #{day} AND time LIKE '%${time}%') AS today ON classroom.id = today.roomId LIMIT #{limitLow},#{limitTop}")
    List<HashMap<String, Object>> selectByPage(@Param("day") int day, @Param("time") int time, @Param("limitLow") int limitLow, @Param("limitTop") int limitTop);

    @Select("SELECT COUNT(classroom.id) AS count FROM classroom RIGHT JOIN (SELECT * FROM courseTable WHERE `day` = #{day} AND time LIKE '%${time}%') AS today ON classroom.id = today.roomId WHERE classroom.id = #{roomId}")
    List<HashMap<String, Object>> check(@Param("day") int day, @Param("time") int time, @Param("roomId") int roomId);
}
