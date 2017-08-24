package com.carlos.luke.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.carlos.luke.model.User;

/**
* @desc    
* @since   2017年8月23日
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RedisTest {

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;
    
    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void testKeys(){
        Set<String> set = stringRedisTemplate.keys("*");
        System.out.println(set.toString());
    }
    
    @Test
    public void testSet(){
        stringRedisTemplate.opsForSet().add("keyb", "11");
        System.out.println(stringRedisTemplate.opsForValue().get("key0")+","+stringRedisTemplate.opsForSet().pop("keyb")+","+stringRedisTemplate.opsForSet().pop("keyb"));
    }
    
    @Test
    public void testOpsForList(){
        List<User> roles = new ArrayList<>();
        User user = new com.carlos.luke.model.User(1L, "luke", 20);
        roles.add(user);
        ValueOperations<String, Object> opsForList = redisTemplate.opsForValue();
        
        opsForList.set("roles", roles);
        
        List<User> result = (List<User>) opsForList.get("roles");
 
        System.out.println(result.get(0).getName());
        
    }
    
    @Test
    public void testOpsForMap(){
        Map<String, String> score = new HashMap<>();
        score.put("stuA", "89");
        score.put("stuB", "63");
        score.put("stuC", "94");
        score.put("stuD", "21");
        
        ValueOperations<String, Object> opsForList = redisTemplate.opsForValue();
        opsForList.set("scores", score);
        Map<String, String> result = (Map<String, String>) opsForList.get("scores");
        System.out.println(result.get("stuA"));
    }
    
    /**
     * BoundKeyOperations、BoundValueOperations、BoundSetOperations
     * BoundListOperations、BoundSetOperations、BoundHashOperations
     */
    @Test
    public void testBoundOperations() {
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("BoundTest");
        //设置值
//        boundValueOperations.set("test12345");
        //设置过期时间
//        boundValueOperations.expire(100, TimeUnit.SECONDS);
        //重命名Key
//        boundValueOperations.rename("BoundTest123");
    
        System.out.println("key: " + boundValueOperations.getKey());
        System.out.println(boundValueOperations.get());
        System.out.println("expire: " + boundValueOperations.getExpire());
    }
    
}
