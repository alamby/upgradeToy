package com.carlos.luke.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Test
    public void testqueryInEvent() throws NumberFormatException, IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader reader = new BufferedReader(new FileReader("D://export//5.txt"));//换成你的文件名
        String line = null;
        List<Long> ids = new ArrayList<>();
        Map<Long, String> map = new HashMap<>();
        Map<Long, String> result = new HashMap<>();
        while((line=reader.readLine())!=null){
            String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
            String id = item[item.length-1];//这就是你要的数据了
            ids.add(Long.parseLong(id));
        }
        List<Event> eventResult = exportDao.queryInEvent(ids);
        
        for (Event event : eventResult) {
            map.put(event.getId(), sdf.format(event.getCreateTime()));
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("D://export//result//5.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Long id : ids) {
//                result.put(id, map.get(id));
            String content = id+"="+map.get(id)+"\r\n";
            try {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
