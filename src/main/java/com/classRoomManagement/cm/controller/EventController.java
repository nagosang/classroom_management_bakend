package com.classRoomManagement.cm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classRoomManagement.cm.entity.Event;
import com.classRoomManagement.cm.mybeans.R;
import com.classRoomManagement.cm.service.EventService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping(value = "/event/getList")
    public R getList(@RequestBody HashMap<String, String> page){
        try {
            return R.ok().put("data", eventService.getList(page));
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @PostMapping(value = "/event/create")
    public R createEvent(@RequestBody HashMap<String, String> event){
        try{
            if (eventService.createEvent(event)){
                return R.ok();
            }
            else {
                return R.error("改时间段被占用");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/event/pass")
    public R pass(@Param("id") int id) {
        try {
            if (eventService.pass(id)){
                return R.ok();
            }
            else {
                return R.error();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/event/notPass")
    public R notPass(@Param("id") int id) {
        try {
            if (eventService.notPass(id)){
                return R.ok();
            }
            else {
                return R.error();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/event/cancel")
    public R cancel(@Param("id") int id) {
        try {
            if (eventService.cancel(id)){
                return R.ok();
            }
            else {
                return R.error();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/event/getResults")
    public R getResult(@Param("id") String id){
        try {
            if(eventService.results(id)){
                return R.ok();
            }
            else{
                return R.error("审核未通过");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
