package com.carlos.luke.dao;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @desc    
* @since   2017年8月23日
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RedisTest {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testKeys(){
        Set<String> set = redisTemplate.keys("*");
        System.out.println(set.toString());
    }
    
    @Test
    public void testSet(){
        Set<String> set = redisTemplate.keys("*");
        System.out.println(set.toString());
    }
    
    @Test
    public void testOpsForList(){
//        redisTemplate.opsForList().leftPush("keyb", 11);
        redisTemplate.opsForSet().add("keyb", "11");
        System.out.println(redisTemplate.opsForValue().get("key0")+","+redisTemplate.opsForSet().pop("keyb")+","+redisTemplate.opsForSet().pop("keyb"));
    }
}
