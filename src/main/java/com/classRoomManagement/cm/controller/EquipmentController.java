package com.classRoomManagement.cm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classRoomManagement.cm.entity.Equipment;
import com.classRoomManagement.cm.mybeans.R;
import com.classRoomManagement.cm.service.EquipmentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(value = "/equipment/getEquipmentList")
    public R getEquipmentList() {
        try {
            return R.ok().put("data", equipmentService.queryList());
        }
        catch (Exception e) {
            e.printStackTrace();
            return R.error("error");
        }
    }

    @PostMapping(value = "/equipment/queryByPage")
    public R queryByPage(@RequestBody HashMap<String, String> page){
        IPage<Equipment> iPage = new Page<>(Integer.parseInt(page.get("page")), Integer.parseInt(page.get("limit")));
        try{
            return R.ok().put("data", equipmentService.queryByPage(iPage));
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error(e.toString());
        }
    }

    @PostMapping(value = "/equipment/createEquipment")
    public R createEquipment(@RequestBody HashMap<String, String> equipmentInfo){
        try {
            if(equipmentService.createEquipment(equipmentInfo)){
                return R.ok();
            }
            else {
                return R.error("error");
            }
        }
        catch (Exception e){
            return R.error(e.toString());
        }
    }

    @PostMapping(value = "/equipment/updateEquipment")
    public R updateEquipment(@RequestBody HashMap<String, String> equipmentInfo){
        try{
            if (equipmentService.updateEquipment(equipmentInfo)){
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

    @GetMapping(value = "/equipment/deleteEquipment")
    public R deleteEquipment(@Param("id") int id){
        try{
            if (equipmentService.deleteEquipment(id)){
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
}
