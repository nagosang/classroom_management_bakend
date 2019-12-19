package com.classRoomManagement.cm.controller;

import com.classRoomManagement.cm.service.ClassroomService;
import com.classRoomManagement.cm.mybeans.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @PostMapping(value = "/classroom/queryByPage")
    public R queryByPage(@RequestBody HashMap<String, String> queryData) {
        try {
            return R.ok().put("data", classroomService.queryByPage(queryData)).put("obj", queryData);
        }
        catch (Exception e) {
            e.printStackTrace();
            return R.error("error");
        }
    }

    @GetMapping(value = "/classroom/getList")
    public R getList(@Param("id") int id){
        try {
            return R.ok().put("data", classroomService.getList(id));
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping(value = "/classroom/newRoom")
    public R createRoom(@RequestBody HashMap<String, String> roomInfo) {
        try{
            if (classroomService.createClassroom(roomInfo)){
                return R.ok();
            }
            else{
                return R.error("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error("error");
        }
    }

    @GetMapping(value = "/classroom/delRoom")
    public R deleteRoom(@Param("id") int id){
        try{
            if(classroomService.deleteClassroom(id)){
                return R.ok();
            }
            else{
                return R.error("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error("error");
        }
    }

    @PostMapping(value = "/classroom/updateRoom")
    public R updateRoom(@RequestBody HashMap<String, String> roomInfo){
        try{
            if(classroomService.updateClassroom(roomInfo)){
                return R.ok();
            }
            else {
                return R.error();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error("error");
        }
    }

    @PostMapping(value = "/classroom/updateEquipment")
    public R updateEquipment(@RequestBody HashMap<String, String>[] equipment, @Param("id") int id){
        try{
            if(classroomService.updateEquipment(equipment,id)){
                return R.ok();
            }
            else {
                return R.error("修改失败");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
