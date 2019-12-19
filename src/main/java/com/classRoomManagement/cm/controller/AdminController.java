package com.classRoomManagement.cm.controller;

import com.classRoomManagement.cm.entity.Admin;
import com.classRoomManagement.cm.mybeans.R;
import com.classRoomManagement.cm.mybeans.Time;
import com.classRoomManagement.cm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping(value = "/login")
    public R login(@RequestBody Admin admin) {
        if (adminService.Login(admin)) {
            return R.ok();
        }
        else {
            return R.error("用户名或密码错误");
        }
    }

    @GetMapping(value = "/getSystemTime")
    public R getSysTime(){
        return R.ok().put("time", Time.getDate());
    }

    @PostMapping(value = "/admin/changePwd")
    public R changePwd(@RequestBody HashMap<String, String> newPwd){
        try {
            if (adminService.changePwd(newPwd)) {
                return R.ok();
            }
            else {
                return R.error();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return R.error(e.toString());
        }
    }
}
