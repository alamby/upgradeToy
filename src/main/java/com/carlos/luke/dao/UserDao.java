package com.carlos.luke.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class UserDao {  
    @ReadThroughSingleCache(namespace = "user",expiration = 15)  
    public User getUserByUsername(@ParameterValueKeyProvider String username) {  
        User userDao = new User();  
        userDao.setUsername("liuchaofan"); 
        userDao.setCount(1);
        userDao.setTime(new Date());
        return userDao;  
    }
   
    public int updateUser(User user) {  
        return 0;  
    }  
   
    @InvalidateSingleCache(namespace = "user")  
    public int deleteUser(@ParameterValueKeyProvider String username) {  
        return 0;  
    }  
   
}  