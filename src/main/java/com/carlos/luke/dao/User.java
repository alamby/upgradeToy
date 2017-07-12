package com.carlos.luke.dao;

import java.io.Serializable;
import java.util.Date;

/**
* @desc    
* @since   2017年7月12日
*
*/
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7637329248540417758L;
    private String username;
    private int count;
    private Date time;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "UserDao [username=" + username + ", count=" + count + "]";
    }
    
    @Override  
    public boolean equals(Object o) {  
        if (this == o) return true;  
        if (!(o instanceof User)) return false;  
   
        User userDao = (User) o;  
   
        if (username != null ? !username.equals(userDao.username) : userDao.username != null) return false;  
   
        return true;  
    }
    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;  
        return result;
    }
    
}
