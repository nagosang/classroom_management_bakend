package com.classRoomManagement.cm.service;

import com.classRoomManagement.cm.entity.Admin;

import java.util.HashMap;

public interface AdminService {
    Boolean Login(Admin admin);
    Boolean changePwd(HashMap<String, String> newPwd);
}
