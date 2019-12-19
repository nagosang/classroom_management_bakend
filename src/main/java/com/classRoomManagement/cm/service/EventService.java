package com.classRoomManagement.cm.service;

import java.util.Date;
import java.util.HashMap;

public interface EventService {
    Object getList(HashMap<String, String> iPage);
    Boolean createEvent(HashMap<String, String> newEvent);
    Boolean pass(int id);
    Boolean notPass(int id);
    Boolean cancel(int id);
    Boolean results(String id);
    void expireEvent();
}
