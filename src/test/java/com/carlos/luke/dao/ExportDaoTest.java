package com.carlos.luke.dao;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.carlos.luke.model.Event;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class ExportDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(ExportDaoTest.class);
	@Autowired
	private ExportDao exportDao;
	
    @Test
    public void testqueryEvent(){
    	Date beginTime = new DateTime(2017, 6, 1, 00, 00, 00).toDate();
    	Date endTime = new DateTime(2017, 6, 19, 23, 59, 59).toDate();
    	String imei = "106013422136661";
    	int type = 10;
    	int pageNo = 1;
    	int pageSize = 10;
        List<Event> eventResult = exportDao.queryEvent(beginTime, endTime,imei, type, pageNo, pageSize);
        logger.info("eventResult : {}.",eventResult);
    }
}
