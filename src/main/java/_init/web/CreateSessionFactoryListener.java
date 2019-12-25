package _init.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import _init.HibernateUtils;

//@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtils.getSessionFactory().close();
	}

	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtils.getSessionFactory();
	}

}
