package com.carlos.luke.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
* @desc    
* @author  刘超凡
* @since   2017年5月17日
*
*/
public class ParseEventMap {
    private static Logger logger = LoggerFactory.getLogger(ParseEventMap.class);
    
    @Test
    public void test_eventMap() {
        String data = "{\"58\":2,\"59\":7}";
        System.out.println(data);
        Map<String, Integer> result = new HashMap<String, Integer>();
        JSONObject jsonObject = JSON.parseObject(data);
        Map<String, Object> dataMap = toMap(jsonObject);
        for (Entry<String, Object> entry : dataMap.entrySet()) {
            String key = entry.getKey();
            if (key == null) {
                continue;
            }
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
            logger.info("key:"+entry.getKey()+",value:"+entry.getValue());
            if (!result.containsKey(entry.getKey())) {
                result.put(entry.getKey(), (Integer)entry.getValue());
            }else {
                Integer tempCount = result.get(key);
                result.put(entry.getKey(), (Integer)entry.getValue()+tempCount);
            }
        }
        String output = JSON.toJSONString(result);
        logger.info("output:"+output);
        System.out.println("output:"+output);
    }
    
    /**
     * JSONObject转为map
     * @param object json对象
     * @return 转化后的Map
     */
    public static Map<String, Object> toMap(JSONObject object){
        Map<String, Object> map = new HashMap<String, Object>();

        for (String key : object.keySet()) {
            Object value = object.get(key);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }

        return map;
    }
    
    /**
     * JSONArray转为List
     * @param array json数组
     * @return 转化后的List
     */
    public static List<Object> toList(JSONArray array){
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
    
}
