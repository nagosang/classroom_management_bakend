package com.classRoomManagement.cm.mybeans;


import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * 固定数据项：code,msg
 * code:状态码；0-成功；!0-失败
 * msg:状态信息
 *
 * {code:1,msg:""}
 *
 * 可自定义数据项
 *
 * for example:
 * return R.ok().put("data",userList).put("total",myPageHelper.getTotal(userList));
 *
 * return R.ok();
 * return R.ok("操作成功");
 *
 * return R.error();
 * return R.error("操作失败");
 *
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R(){
        put("code",0);
        put("msg","success");
    }

    public static R error(){return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知错误，请联系管理员");}

    public static R error(String msg){ return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg); }

    public static R error(int code, String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
