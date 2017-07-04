package com.carlos.luke.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlos.luke.dao.ExportDao;
import com.carlos.luke.model.Event;
import com.carlos.luke.utils.CsvUtil;


public class LoopQueryTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(LoopQueryTask.class);
	private ExportDao exportDao;

	public LoopQueryTask(ExportDao exportDao) {
		this.exportDao = exportDao;
	}

	@Override
	public void run() {
		try {
			loopQuery();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public void loopQuery() throws InterruptedException {
    	logger.info("loopQuery start :"+DateTime.now());
        File file = new File("D://input.txt");  
        BufferedReader reader = null;  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
                System.out.println("line " + line + ": " + tempString);  
                line++;
                queryEvent(tempString, 100);
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
    	logger.info("loopQuery done :"+DateTime.now());
    }

//    @Scheduled(fixedRate = 1000*120)
    public void queryEvent(String imei,int pageSize) throws InterruptedException {  
    	Date beginTime = new DateTime(2017, 6, 1, 00, 00, 00).toDate();
    	Date endTime = new DateTime(2017, 6, 19, 23, 59, 59).toDate();
    	int type = 10;
    	int pageNo = 0;
    	List<Event> result = new ArrayList<Event>();
    	List<Event> eventResult = new ArrayList<Event>();
        do {
        	eventResult = exportDao.queryEvent(beginTime, endTime,imei, type, pageNo, pageSize);
        	result.addAll(eventResult);
        	pageNo=pageNo+100;
        	Thread.sleep(1000);
		} while (eventResult.size() == 100);
        Collections.sort(result, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                long xxx = event1.getBeginTime().getTime() - event2.getBeginTime().getTime();
                return Integer.parseInt(String.valueOf(xxx));
            }
        });
        logger.info("size:"+result.size()+",result:"+result);
        
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "id(编号)");  
        map.put("2", "sysid(系统ID)");  
        map.put("3", "imei(设备IMEI)");  
        map.put("4", "trucknum(车辆编号)");
        map.put("5", "driverno(司机编号)");
        map.put("6", "orgcode(机构号)");
        map.put("7", "type(10/11为进出区域)");
        map.put("8", "beginLng(开始经度)");
        map.put("9", "beginLat(开始纬度)");
        map.put("10", "beginTime(开始时间)");
        map.put("11", "endLng(结束经度)");
        map.put("12", "endLat(结束纬度)");
        map.put("13", "endTime(结束时间)");
        map.put("14", "triggerTime(触发时间)");
        map.put("15", "triggerLng(触发经度)");
        map.put("16", "triggerLat(触发纬度)");
        map.put("17", "createTime(创建时间)");
        map.put("18", "updateTime(更新时间)");
        map.put("19", "seconds(持续时间|秒)");
        map.put("20", "additional_info(附加信息)");
        map.put("21", "additional_key(额外key)");
        map.put("22", "truckno(车牌号)");
  
        String path = "D://export//";  
        String fileName = imei;  
        String fileds[] = new String[] { "id", "sysid", "imei", "trucknum", "driverno", "orgcode", "type", "beginLng", "beginLat", "beginTime", "endLng", "endLat", "endTime", "triggerTime", "triggerLng", "triggerLat", "createTime", "updateTime", "seconds", "additional_info", "additional_key" ,"truckno"};// 设置列英文名（也就是实体类里面对应的列名）  
        File file = CsvUtil.createCSVFile(result, fileds, map, path,  
                fileName);
        System.out.println(file.getName());
    }	
	
}
