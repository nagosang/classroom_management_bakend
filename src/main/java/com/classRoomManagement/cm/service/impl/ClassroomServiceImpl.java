package com.classRoomManagement.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.classRoomManagement.cm.entity.Classroom;
import com.classRoomManagement.cm.mapper.ClassroomMapper;
import com.classRoomManagement.cm.mybeans.Time;
import com.classRoomManagement.cm.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public Object queryByPage(HashMap<String, String> listQuery) {
        try{
            int day = Time.getDay();
            int time = Time.getTimes();
            int page = Integer.parseInt(listQuery.get("page"));
            int limit = Integer.parseInt(listQuery.get("limit"));
            int limitLow = (page - 1) * limit;
            int limitTop = page * limit;
            HashMap<String, Object> result = new HashMap<>();
            if (listQuery.get("buildId").equals("")) {
                result.put("records", classroomMapper.selectByPage(day, time, limitLow, limitTop));
                result.put("total", classroomMapper.selectCount(null));
                return result;
            }
            else{
                int buildId = Integer.parseInt(listQuery.get("buildId"));
                QueryWrapper<Classroom> classroomQueryWrapper =new QueryWrapper<>();
                classroomQueryWrapper.eq("buildId", buildId);
                result.put("records", classroomMapper.selectByBuild(day, time, buildId, limitLow, limitTop));
                result.put("total", classroomMapper.selectCount(classroomQueryWrapper));
                return result;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object getList(int id) {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buildId", id);
        try{
            return classroomMapper.selectList(queryWrapper);
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean createClassroom(HashMap<String, String> roomInfo) {
        int buildId = Integer.parseInt(roomInfo.get("buildId"));
        String roomId = roomInfo.get("roomId");
        int floor = Integer.parseInt(roomInfo.get("floor"));
        QueryWrapper<Classroom> classroomQueryWrapper = new QueryWrapper<>();
        classroomQueryWrapper.eq("buildId", buildId).eq("roomId", roomId);
        if(classroomMapper.selectCount(classroomQueryWrapper) >= 1){
            return false;
        }
        Classroom newRoom = new Classroom();
        newRoom.setBuildId(buildId);
        newRoom.setRoomId(roomId);
        newRoom.setFloor(floor);
        newRoom.setEquipment("");
        newRoom.setEquipment("{}");
        try{
            if(classroomMapper.insert(newRoom) == 1){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Boolean deleteClassroom(int id) {
        try {
            if(classroomMapper.deleteById(id)==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateClassroom(HashMap<String, String> roomInfo) {
        int id = Integer.parseInt(roomInfo.get("id"));
        int buildId = Integer.parseInt(roomInfo.get("buildId"));
        String roomId = roomInfo.get("roomId");
        int floor = Integer.parseInt(roomInfo.get("floor"));
        try {
            Classroom classroom = classroomMapper.selectById(id);
            classroom.setBuildId(buildId);
            classroom.setRoomId(roomId);
            classroom.setFloor(floor);
            if(classroomMapper.updateById(classroom) == 1){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean updateEquipment(HashMap<String, String>[] equipment, int id) {
        String strEquipment = "{";
        for(int i=0;i<equipment.length;i++){
            strEquipment += "\"" + equipment[i].get("id") + "\":";
            strEquipment += equipment[i].get("number");
            if(i+1<equipment.length){
                strEquipment += ",";
            }
        }
        strEquipment += "}";
        try {
            Classroom classroom = classroomMapper.selectById(id);
            classroom.setEquipment(strEquipment);
            if(classroomMapper.updateById(classroom) == 1){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
