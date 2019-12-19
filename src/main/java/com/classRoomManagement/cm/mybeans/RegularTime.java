package com.classRoomManagement.cm.mybeans;


import com.classRoomManagement.cm.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class RegularTime {
    @Autowired
    private EventService eventService;

    // 1小时执行一次
    @Scheduled(fixedDelay = 1000 * 60 * 60)
    private void configureTasks() {
        System.err.println("执行定时任务时间: " + LocalDateTime.now());
        eventService.expireEvent();
    }
}
