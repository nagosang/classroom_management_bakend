package com.classRoomManagement.cm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classRoomManagement.cm.entity.Build;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface BuildMapper extends BaseMapper<Build> {
    @Select("SELECT build.id,build.`name`,build.notes,COUNT(build.id) AS count FROM build LEFT JOIN classroom ON build.id = classroom.buildId GROUP BY build.id LIMIT #{limitLow},#{limitTop}")
    List<HashMap<String, Object>> queryList(@Param("limitLow") int limitLow, @Param("limitTop") int limitTop);
}
