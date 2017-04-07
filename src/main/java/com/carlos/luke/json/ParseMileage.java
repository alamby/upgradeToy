package com.carlos.luke.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
* @desc    
* @since   2017年4月7日
*
*/
public class ParseMileage {

    /*
     * 解析json中的mileage字段
     */
    @Test
    public void test_dayData() {
        File file = new File("dayData.json");
        String json = readToString(file, "UTF-8");
//        JSONArray dataArray = JSON.parseArray(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.toJSONString());
        double result = 0;
//        for (int i = 0; i < dataArray.size(); i++) {
//            JSONObject temp = JSON.parseObject(json);
//            result += Double.parseDouble(temp.get("mileage").toString());
//        }
//        System.out.println(result);
    }
    
    /*
     * 解析json中的mileage字段
     */
    @Test
    public void test_parseMileage() {
        File file = new File("mileage.json");
        String json = readToString(file, "UTF-8");
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray dataArray = data.getJSONArray("result");
        double result = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject temp = JSON.parseObject(dataArray.get(i).toString());
//            System.out.println("carNumber:"+temp.get("carNumber")+",mileage:"+temp.get("mileage"));
            result += Double.parseDouble(temp.get("mileage").toString());
        }
        System.out.println(result);
    }

    /*
     * 读整个文本文件
     */
    public String readToString(File file, String encoding) {
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
            } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
