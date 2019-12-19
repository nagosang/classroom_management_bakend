package com.classRoomManagement.cm.controller;

import com.classRoomManagement.cm.mybeans.R;
import com.classRoomManagement.cm.service.BuildService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BuildController {
    @Autowired
    private BuildService buildService;

    @GetMapping(value = "/build/getBuild")
    public R getBuild(){
        try {
            return R.ok().put("data", buildService.getList());
        }
        catch (Exception e) {
            e.printStackTrace();
            return R.error("error");
        }
    }

    @GetMapping(value = "/build/queryList")
    public R queryList(@Param("page") int page){
        try{
            return R.ok().put("data", buildService.queryList(page));
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error("error");
        }
    }

    @PostMapping(value = "/build/updateBuild")
    public R updateBuild(@RequestBody HashMap<String,String> buildInfo){
        try{
            if(buildService.updateBuild(buildInfo)){
                return R.ok();
            }
            else{
                return R.error("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @PostMapping(value = "/build/createBuild")
    public R createBuild(@RequestBody HashMap<String, String> buildInfo){
        try{
            if(buildService.createBuild(buildInfo)){
                return R.ok();
            }
            else {
                return R.error("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/build/deleteBuild")
    public R deleteBuild(@Param("id") int id){
        try{
            if (buildService.deleteBuild(id)){
                return R.ok();
            }
            else{
                return R.error("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }
}
