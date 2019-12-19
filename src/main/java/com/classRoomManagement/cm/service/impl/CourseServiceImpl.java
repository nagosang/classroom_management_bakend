package com.classRoomManagement.cm.service.impl;

import com.classRoomManagement.cm.mapper.CourseMapper;
import com.classRoomManagement.cm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public String getCourseName(int id) {
        try{
            return courseMapper.selectById(id).getCourseName();
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
