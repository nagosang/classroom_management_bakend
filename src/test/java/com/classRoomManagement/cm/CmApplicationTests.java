package com.classRoomManagement.cm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.classRoomManagement.cm.entity.Event;
import com.classRoomManagement.cm.mapper.ClassroomMapper;
import com.classRoomManagement.cm.mapper.EventMapper;
import com.classRoomManagement.cm.mybeans.Time;
import com.classRoomManagement.cm.service.CourseService;
import com.classRoomManagement.cm.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class CmApplicationTests {

	@Autowired
	private EventService eventService;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		try {
			eventService.expireEvent();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
