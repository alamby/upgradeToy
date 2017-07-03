package com.carlos.luke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
* @desc    
* @since   2017年7月3日
*
*/
public class CsvMergeByDay {
    
    public static void main(String[] args) {
        try {
            readfile("D://export");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 读取文件夹下的所有csv文件,并按照其中的beginTime分文件夹存储
     */
    public static Boolean readfile(String filepath) throws FileNotFoundException, IOException {
        String path = "D://byday//";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss 'CST' yyyy",Locale.ENGLISH);
            try {
                    File file = new File(filepath);
                    if (!file.isDirectory()) {
                            System.out.println("文件,path=" + file.getPath()+"absolutepath=" + file.getAbsolutePath()+"name=" + file.getName());

                    } else if (file.isDirectory()) {
                            System.out.println("文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                        //读取csv
                                        CSVReader reader = new CSVReader(new FileReader(readfile.getAbsolutePath()));
                                        List<String[]> list = reader.readAll();
                                        Map<Integer, List<String[]>> byDayMap = new HashMap<>();
                                        int firstCount = 0;
                                        for (String[] line : list) {
                                            firstCount++;
                                            if (firstCount == 1) {
                                                //跳过header
                                                continue;
                                            }
                                            Date beginTime = sdf.parse(line[9]);
//                                            System.out.println("beginTime:"+beginTime);
                                            if (byDayMap.containsKey(beginTime.getDay())) {
                                                List<String[]> lines = byDayMap.get(beginTime.getDay());
                                                lines.add(line);
                                                byDayMap.put(getDay(beginTime), lines);
                                            }else {
                                                List<String[]> lines = new ArrayList<>();
                                                lines.add(line);
                                                byDayMap.put(getDay(beginTime), lines);
                                            }
                                        }
                                        for (Map.Entry<Integer, List<String[]>> entry : byDayMap.entrySet()) {
                                            System.out.println("path:"+path+entry.getKey()+"//"+readfile.getName());
                                            write2File(path+entry.getKey()+"//"+readfile.getName(), entry.getValue());
                                        }
                                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }
                    }
            } catch (FileNotFoundException | ParseException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return true;
    }
    
    public static void write2File(String filePath,List<String[]> lines) {
        //写之前需要先写入header
        List<String[]> addHeader = new ArrayList<>();
        String[] header = new String[21];
        header[0] = "id(编号)";
        header[1] = "sysid(系统ID)";
        header[2] = "imei(设备IMEI)";
        header[3] = "trucknum(车辆编号)";
        header[4] = "driverno(司机编号)";
        header[5] = "orgcode(机构号)";
        header[6] = "type(10为进出区域)";
        header[7] = "beginLng(开始经度)";
        header[8] = "beginLat(开始纬度)";
        header[9] = "beginTime(开始时间)";
        header[10] = "endLng(结束经度)";
        header[11] = "endLat(结束纬度)";
        header[12] = "endTime(结束时间)";
        header[13] = "triggerTime(触发时间)";
        header[14] = "triggerLng(触发经度)";
        header[15] = "triggerLat(触发纬度)";
        header[16] = "createTime(创建时间)";
        header[17] = "updateTime(更新时间)";
        header[18] = "seconds(持续时间|秒)";
        header[19] = "additional_info(附加信息)";
        header[20] = "additional_key(额外key)";
        addHeader.add(header);
        addHeader.addAll(lines);
        try {
            Charset encoder = Charset.forName("GBK");//解决乱码
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), encoder); 
            CSVWriter writer = new CSVWriter(out, ',');
            writer.writeAll(addHeader);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 获取天
     */
    public static Integer getDay(Date date) {
        if (date == null)
          return 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
      }
}
