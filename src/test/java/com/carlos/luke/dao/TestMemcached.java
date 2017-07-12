package com.carlos.luke.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.spy.memcached.MemcachedClient;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class TestMemcached {  
    @Autowired
    private MemcachedClient client;
    
    @Test  
    public void testGetUserByUsername(){
        System.out.println("value1:"+client.get("liuchaofan"));
        client.add("liuchaofan", 10, "{\"username\": \"liuchaofan\",     \"count\": 1,     \"time\": \"2017-07-12 15:00:00\" }");
        System.out.println("value2:"+client.get("liuchaofan"));
    }  
}  