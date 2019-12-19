package com.classRoomManagement.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.classRoomManagement.cm.entity.Admin;
import com.classRoomManagement.cm.mapper.AdminMapper;
import com.classRoomManagement.cm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Boolean Login(Admin admin) {
        try{
            admin.getAdmin();
            QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
            adminQueryWrapper.eq("admin", admin.getAdmin());
            Admin loginAdmin = adminMapper.selectOne(adminQueryWrapper);
            if (loginAdmin == null) {
                return false;
            }
            if (loginAdmin.getPassword().equals(admin.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Boolean changePwd(HashMap<String, String> newPwd) {
        String id = newPwd.get("admin");
        String password = newPwd.get("password");
        String newPassword = newPwd.get("newPassword");
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("admin", id).eq("password", password);
        try{
            if (adminMapper.selectCount(adminQueryWrapper) == 0){
                return false;
            }
            Admin admin = adminMapper.selectOne(adminQueryWrapper);
            admin.setPassword(newPassword);
            if (adminMapper.updateById(admin) == 1) {
                return true;
            }
            else {
                return  false;
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
