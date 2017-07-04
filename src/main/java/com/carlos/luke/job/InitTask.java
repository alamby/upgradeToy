package com.carlos.luke.job;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carlos.luke.dao.ExportDao;
import com.chinaway.gps.card.service.GpsCardService;


/**
 * 定时查询MYSQL任务  
 */
@Component
public class InitTask implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(InitTask.class);
	@Autowired
	private ExportDao exportDao;
    @Autowired
    private GpsCardService gpsCardService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread t = new Thread(new LoopQueryTask(exportDao,gpsCardService));
		t.start();
	}

    /**
     * 启动时执行一次，之后每隔5秒执行一次  
     */
//    @Scheduled(fixedRate = 1000*5)
    public void print() {
    	logger.info("print method :"+DateTime.now());
    }

}