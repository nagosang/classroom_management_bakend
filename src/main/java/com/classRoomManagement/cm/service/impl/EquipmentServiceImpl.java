package com.classRoomManagement.cm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classRoomManagement.cm.entity.Equipment;
import com.classRoomManagement.cm.mapper.EquipmentMapper;
import com.classRoomManagement.cm.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Object queryList() {
        try{
            return equipmentMapper.selectList(null);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object queryByPage(IPage<Equipment> page) {
        try{
            return equipmentMapper.selectPage(page,null);
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean createEquipment(HashMap<String, String> equipmentInfo) {
        String equipment = equipmentInfo.get("equipment");
        String equipmentComment = equipmentInfo.get("comment");
        String unit = equipmentInfo.get("unit");
        Equipment newEquipment = new Equipment();
        newEquipment.setEquipment(equipment);
        newEquipment.setEquipmentComment(equipmentComment);
        newEquipment.setUnit(unit);
        try{
            if (equipmentMapper.insert(newEquipment) == 1){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean updateEquipment(HashMap<String, String> equipmentInfo) {
        int id = Integer.parseInt(equipmentInfo.get("id"));
        String equipment = equipmentInfo.get("equipment");
        String equipmentComment = equipmentInfo.get("comment");
        String unit = equipmentInfo.get("unit");
        try {
            Equipment newEquipment = equipmentMapper.selectById(id);
            newEquipment.setEquipment(equipment);
            newEquipment.setEquipmentComment(equipmentComment);
            newEquipment.setUnit(unit);
            if(equipmentMapper.updateById(newEquipment) == 1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean deleteEquipment(int id) {
        try{
            if(equipmentMapper.deleteById(id) == 1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            throw e;
        }
    }
}
