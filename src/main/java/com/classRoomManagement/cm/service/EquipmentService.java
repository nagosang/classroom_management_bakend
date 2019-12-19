package com.classRoomManagement.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classRoomManagement.cm.entity.Equipment;

import java.util.HashMap;

public interface EquipmentService {
    Object queryList();
    Object queryByPage(IPage<Equipment> page);
    Boolean createEquipment(HashMap<String, String> equipmentInfo);
    Boolean updateEquipment(HashMap<String, String> equipmentInfo);
    Boolean deleteEquipment(int id);
}
