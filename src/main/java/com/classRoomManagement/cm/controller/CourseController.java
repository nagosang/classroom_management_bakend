package com.classRoomManagement.cm.controller;

import com.classRoomManagement.cm.mybeans.R;
import com.classRoomManagement.cm.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/course/getName")
    public R getName(@Param("id") int id){
        try{
            return R.ok().put("name",courseService.getCourseName(id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return R.error("error");
        }
    }
}
