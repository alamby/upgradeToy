package com.carlos.luke.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import com.carlos.luke.model.Event;

public class CsvUtil {
	/** 
     * 生成为CVS文件 
     *  
     * @param exportData 
     *            源数据List 
     * @param fileds 
     * @param map 
     *            csv文件的列表头map 
     * @param outPutPath 
     *            文件路径 
     * @param fileName 
     *            文件名称 
     * @return 
     */  
    @SuppressWarnings("rawtypes")  
    public static File createCSVFile(List exportData, String[] fileds,  
            LinkedHashMap map, String outPutPath, String fileName,String truckno) {
        if (exportData == null || exportData.isEmpty()) {
            return null;
        }
        File csvFile = null;  
        BufferedWriter csvFileOutputStream = null;  
        try {
            File file = new File(outPutPath);  
            if (!file.exists()) {  
                file.mkdir();  
            }  
            // 定义文件名格式并创建
            csvFile = new File("D://export//"+fileName+".csv");
            System.out.println("csvFile：" + csvFile);  
            // UTF-8使正确读取分隔符","  
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(csvFile), "GBK"), 1024);  
//            System.out.println("csvFileOutputStream：" + csvFileOutputStream);  
            // 写入文件头部  
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator  
                    .hasNext();) {  
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator  
                        .next();  
                csvFileOutputStream  
                        .write((String) propertyEntry.getValue() != null ? new String(  
                                ((String) propertyEntry.getValue())  
                                        .getBytes("GBK"), "GBK") : "");  
                if (propertyIterator.hasNext()) {  
                    csvFileOutputStream.write(",");  
                }  
//                System.out.println(new String(((String) propertyEntry  
//                        .getValue()).getBytes("GBK"), "GBK"));  
            }  
            csvFileOutputStream.write("\r\n");  
            // 写入文件内容,  
            // ============ //第一种格式：Arraylist<实体类>填充实体类的基本信息==================
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss 'CST' yyyy",Locale.ENGLISH);
            SimpleDateFormat sdfNormal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; exportData != null && !exportData.isEmpty()  
                    && j < exportData.size(); j++) {  
                Event t = (Event) exportData.get(j);  
                Class clazz = t.getClass();  
                String[] contents = new String[fileds.length];  
                for (int i = 0; fileds != null && i < fileds.length; i++) {  
                    String filedName = toUpperCaseFirstOne(fileds[i]);  
                    Method method = clazz.getMethod(filedName);  
                    method.setAccessible(true);  
                    Object obj = method.invoke(t);  
                    String str = String.valueOf(obj);

                    if (str == null || str.equals("null"))  
                        str = "";
                    contents[i] = str;
                }  
  
                for (int n = 0; n < contents.length; n++) {  
                    // 将生成的单元格添加到工作表中,时间转格式
                    if ((n==9|n==12|n==13|n==16|n==17) && !(contents[n] == null || contents[n].equals(""))) {
                        Date beginTime = sdf.parse(contents[n]);
                        contents[n] = sdfNormal.format(beginTime);
                    }
                    csvFileOutputStream.write(csvHandlerStr(contents[n]));
                    if (n<=20) {//给车牌列留空
                        csvFileOutputStream.write(",");
                    }
                }
                csvFileOutputStream.write(truckno);//补充车牌
                csvFileOutputStream.write("\r\n");
            }
            csvFileOutputStream.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                csvFileOutputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return csvFile;  
    }

    /** 
     * 测试数据 
     *  
     * @param args 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static void main(String[] args) {  
        // 获取农商银行的数据:true代表是农商  
        // =======改成list的格式，支持（Arraylist传入实体类的形式），改造的方法============  
        ArrayList<Event> eventList = new ArrayList<Event>();  
        Event event = new Event();
        event.setId(11);
  
        Event event2 = new Event();
        event2.setId(22);
        event2.setImei("{\"distance\":95,\"oid\":\"3833D9CCC52731948F636F0952E321502F\",\"ponintId\":192370,\"nextId\":3710722405}");
  
        eventList.add(event);
        eventList.add(event2);
        
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "第一列");  
        map.put("2", "第二列");  
        map.put("3", "第三列");  
        map.put("4", "第四列");  
  
        String path = "D://";  
        String fileName = "test";  
        String truckno = "truckno";  
        String fileds[] = new String[] { "id", "imei" };// 设置列英文名（也就是实体类里面对应的列名）  
        File file = CsvUtil.createCSVFile(eventList, fileds, map, path,  
                fileName,truckno);
        if (file != null) {
            String fileName2 = file.getName();  
            System.out.println("文件名称：" + fileName2);  
        }
    }
    
    /** 
     * 方法名称: csvHandlerStr</br> 
     * 方法描述: 处理包含逗号，或者双引号的字段</br> 
     * 方法参数: @param forecastName 
     * 方法参数: @return  </br> 
     * 返回类型: String</br> 
     * 抛出异常:</br> 
     */   
     public static String csvHandlerStr(String str) {  
         //csv格式如果有逗号，整体用双引号括起来；如果里面还有双引号就替换成两个双引号，这样导出来的格式就不会有问题了             
         String tempDescription=str;  
         //如果有逗号  
         if(str.contains(",")){                
             //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误  
             if(str.contains("\"")){  
                 tempDescription=str.replace("\"", "\"\"");  
             }  
             //在将逗号转义  
             tempDescription="\""+tempDescription+"\"";  
         }  
         return tempDescription;  
     }
    
    /** 
     * 将第一个字母转换为大写字母并和get拼合成方法 
     *  
     * @param origin 
     * @return 
     */  
    private static String toUpperCaseFirstOne(String origin) {  
        StringBuffer sb = new StringBuffer(origin);  
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));  
        sb.insert(0, "get");  
        return sb.toString();  
    }
}
