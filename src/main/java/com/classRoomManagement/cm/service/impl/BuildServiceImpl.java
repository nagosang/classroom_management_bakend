package com.classRoomManagement.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.classRoomManagement.cm.entity.Build;
import com.classRoomManagement.cm.mapper.BuildMapper;
import com.classRoomManagement.cm.service.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BuildServiceImpl implements BuildService {
    @Autowired
    private BuildMapper buildMapper;

    @Override
    public Object getList() {
        try{
            return buildMapper.selectList(null);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object queryList(int page) {
        int limitLow = (page - 1) * 8;
        int limitTop = page * 8;
        HashMap<String, Object> result = new HashMap<>();
        try{
            result.put("records", buildMapper.queryList(limitLow, limitTop));
            result.put("total", buildMapper.selectCount(null));
            return result;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean updateBuild(HashMap<String, String> buildInfo) {
        int id = Integer.parseInt(buildInfo.get("id"));
        String name = buildInfo.get("name");
        String notes = buildInfo.get("notes");
        try{
            Build build = buildMapper.selectById(id);
            build.setName(name);
            build.setNotes(notes);
            if (buildMapper.updateById(build) == 1){
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
    public Boolean createBuild(HashMap<String, String> buildInfo) {
        String name = buildInfo.get("name");
        String notes = buildInfo.get("notes");
        Build build = new Build();
        build.setName(name);
        build.setNotes(notes);
        try{
            if(buildMapper.insert(build) == 1){
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
    public Boolean deleteBuild(int id) {
        try{
            if (buildMapper.deleteById(id) == 1){
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
