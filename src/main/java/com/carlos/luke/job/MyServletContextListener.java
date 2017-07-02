package com.carlos.luke.job;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

public class MyServletContextListener implements ServletContextListener {
	@Autowired
	private InitTask initTask;
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	System.out.println("alamby:here can't init the InitTask");
    	initTask.print();
    }
}