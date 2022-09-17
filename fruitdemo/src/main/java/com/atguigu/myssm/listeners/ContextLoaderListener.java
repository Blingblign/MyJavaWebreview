package com.atguigu.myssm.listeners;

import com.atguigu.myssm.factory.impl.ClassPathXmlApplicationContext;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author bling
 * @create 2022-09-16 18:21
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        //读取ioc容器配置文件位置
        String applicationConfigurationPath = servletContext.getInitParameter("applicationConfigurationPath");
        ClassPathXmlApplicationContext classPathXmlApplicationContext;
        if (!StringUtils.isNullOrEmpty(applicationConfigurationPath)) {
            //创建ioc容器
            classPathXmlApplicationContext = new ClassPathXmlApplicationContext(applicationConfigurationPath);
        }else {
            classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        }
        servletContext.setAttribute("classPathXmlApplicationContext", classPathXmlApplicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
