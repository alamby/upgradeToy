package com.carlos.luke.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.carlos.luke.model.Event;
import com.carlos.luke.model.TerminalAlarmInfo;
import com.chinaway.gps.card.domain.GpsCard;
import com.chinaway.gps.card.service.GpsCardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class ExportDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(ExportDaoTest.class);
	@Autowired
	private ExportDao exportDao;
    @Autowired
    private GpsCardService gpsCardService;	
	
	/*
	 * imei     start_time>(最后定位时间-1d)   最后定位时间<end_time   alarm_type=1    alarm_status = 0/1（同时的关系）  
     * 看start_time在3秒内相同的
	 */
    @Test
    public void testqueryAlarmInRange() throws ParseException{
        List<Set<String>> list = new ArrayList<>();
        List<String> gpsnotime = readFile("D://in.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < gpsnotime.size(); i++) {  //i=10;i< 20;i++
            int gpsnoi = Integer.valueOf(gpsnotime.get(i).split(",")[0]);
            GpsCard gpsCardi = gpsCardService.getByGpsNo(gpsnoi);
            Date timei = sdf.parse(gpsnotime.get(i).split(",")[1]);
            DateTime endi = new DateTime(timei);
            DateTime starti = new DateTime(timei).minusDays(1);
            List<TerminalAlarmInfo> alarmi = exportDao.queryAlarm0(gpsCardi.getGpsid(),starti.toDate(),endi.toDate());
            if (alarmi == null||alarmi.isEmpty()) {
                if (i/10 ==0) {
                    System.out.println("inneri:"+gpsnoi);
                }
                continue;
            }
            
            for (int j = i+1; j < gpsnotime.size(); j++) {  //j=11;j<20;j++
                Set<String> set = new HashSet<>();
                int gpsnoj = Integer.valueOf(gpsnotime.get(j).split(",")[0]);
                GpsCard gpsCardj = gpsCardService.getByGpsNo(gpsnoj);
                Date timej = sdf.parse(gpsnotime.get(j).split(",")[1]);
                DateTime endj = new DateTime(timej);
                DateTime startj = new DateTime(timej).minusDays(1);
                List<TerminalAlarmInfo> alarmj = exportDao.queryAlarm1(gpsCardj.getGpsid(),startj.toDate(),endj.toDate());
                if (alarmj == null || alarmj.isEmpty()) {
                    if (j/10 == 0) {
                        System.out.println("innerj:"+gpsnoj);
                    }
                    continue;
                }
                for (TerminalAlarmInfo alarmiout : alarmi) {
                    for (TerminalAlarmInfo alarmjout : alarmj) {
                        if (j/20 == 0) {
                            System.out.println("calc:"+alarmiout.getStart_time()+","+alarmjout.getStart_time());
                        }
                        if (Math.abs(alarmiout.getStart_time().getTime() -alarmjout.getStart_time().getTime()) <=5000) {
                            set.add(gpsnotime.get(i).split(",")[0]);
                            set.add(gpsnotime.get(j).split(",")[0]);
                            System.out.println("set:"+set+","+(alarmiout.getStart_time().getTime() -alarmjout.getStart_time().getTime()));
                            list.add(set);
                        }
                    }
                }
            }
        }
        System.out.println("result:"+list);
    }
	
    @Test
    public void testqueryAlarm0() throws ParseException{
        List<String> gpsnotime = readFile("D://in.txt");
        int gpsno = Integer.valueOf(gpsnotime.get(4).split(",")[0]);
        GpsCard gpsCard = gpsCardService.getByGpsNo(gpsno);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startRaw = sdf.parse(gpsnotime.get(4).split(",")[1]);
        DateTime end = new DateTime(startRaw);
        DateTime start = new DateTime(startRaw).minusDays(1);
        System.out.println(gpsCard.getGpsid()+start.toDate()+end.toDate());
        List<TerminalAlarmInfo> alarm = exportDao.queryAlarm0(gpsCard.getGpsid(),start.toDate(),end.toDate());
        System.out.println("alarm:"+alarm);
    }
    
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

    public static List<String> readFile(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }
}
