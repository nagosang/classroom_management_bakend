package com.classRoomManagement.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classRoomManagement.cm.entity.Event;
import com.classRoomManagement.cm.mapper.ClassroomMapper;
import com.classRoomManagement.cm.mapper.EventMapper;
import com.classRoomManagement.cm.mybeans.Time;
import com.classRoomManagement.cm.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadMXBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public Object getList(HashMap<String, String> iPage) {
        int page = Integer.parseInt(iPage.get("page"));
        int limit = Integer.parseInt(iPage.get("limit"));
        int limitLow = (page - 1) * limit;
        int limitTop = page * limit;
        HashMap<String, Object> result = new HashMap<>();
        try{
            result.put("records", eventMapper.selectByPage(limitLow, limitTop));
            result.put("total",eventMapper.selectCount(null));
            return result;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean createEvent(HashMap<String, String> newEvent) {
        Event event = new Event();
        event.setName(newEvent.get("name"));
        event.setComment((newEvent.get("comment")));
        event.setProposerId(newEvent.get("proposerId"));
        event.setProposerName(newEvent.get("proposerName"));
        event.setProposerDay(Time.toString(Time.getCurrentDate()));
        event.setDay(newEvent.get("day"));
        event.setTime(newEvent.get("time"));
        event.setRoomId(Integer.parseInt(newEvent.get("roomId")));
        event.setEndTIme(Time.stringTomorrow(newEvent.get("day")));
        event.setSituation(0);

        int day = Time.getDay(Time.strToDate(newEvent.get("day")));
        String time = newEvent.get("time");
        try{
            for(int i = 0;i<time.length();i++){
                if(time.charAt(i) != ',') {
                    if(Integer.parseInt(classroomMapper.check(day, Integer.parseInt(String.valueOf(time.charAt(i))),Integer.parseInt(newEvent.get("roomId"))).get(0).get("count").toString()) >= 1){
                        return false;
                    }
                    QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
                    eventQueryWrapper.eq("roomId", newEvent.get("roomId"));
                    eventQueryWrapper.like("time","%" + time.charAt(i) + "%");
                    eventQueryWrapper.eq("day", newEvent.get("day"));
                    if(eventMapper.selectCount(eventQueryWrapper) >= 1){
                        return false;
                    }
                }
            }
            if (eventMapper.insert(event) == 1){
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
    public Boolean pass(int id) {
        try{
            Event event = eventMapper.selectById(id);
            event.setSituation(1);
            if (eventMapper.updateById(event) == 1){
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
    public Boolean notPass(int id) {
        try{
            Event event = eventMapper.selectById(id);
            event.setSituation(2);
            if (eventMapper.updateById(event) == 1){
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
    public Boolean cancel(int id) {
        try{
            Event event = eventMapper.selectById(id);
            event.setSituation(4);
            if (eventMapper.updateById(event) == 1){
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
    public Boolean results(String id) {
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("proposerId", id).orderByDesc("id");
        try {
            if (eventMapper.selectCount(eventQueryWrapper) < 1){
                return false;
            }
            else if (eventMapper.selectList(eventQueryWrapper).get(0).getSituation() != 1){
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e){
            throw e;

        }
    }

    @Override
    public void expireEvent() {
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("situation",0).or().eq("situation",1);
        try {
            Date nowDate = Time.getCurrentDate();
            eventMapper.selectList(eventQueryWrapper).forEach(item -> {
                if (Time.strToDate(item.getEndTIme()).before(nowDate)){
                    item.setSituation(3);
                    eventMapper.updateById(item);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
