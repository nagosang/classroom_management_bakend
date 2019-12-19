package com.classRoomManagement.cm.service;

import java.util.HashMap;

public interface BuildService {
    Object getList();

    Object queryList(int page);

    Boolean updateBuild(HashMap<String, String> buildInfo);

    Boolean createBuild(HashMap<String, String> buildInfo);

    Boolean deleteBuild(int id);
}
