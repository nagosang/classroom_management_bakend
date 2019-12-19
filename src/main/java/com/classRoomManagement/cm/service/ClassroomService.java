package com.classRoomManagement.cm.service;

import java.util.HashMap;

public interface ClassroomService {
    Object queryByPage(HashMap<String, String> listQuery);
    Object getList(int id);
    Boolean createClassroom(HashMap<String, String> roomInfo);
    Boolean deleteClassroom(int id);
    Boolean updateClassroom(HashMap<String, String> roomInfo);
    Boolean updateEquipment(HashMap<String, String>[] equipment, int id);
}
