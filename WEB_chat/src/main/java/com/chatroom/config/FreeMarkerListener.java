package com.chatroom.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author yida
 */

@WebListener
public class FreeMarkerListener implements ServletContextListener {
    //设置参数
    public static final String TEMPLATE_KEY="_template_";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //配置版本
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_0);


        //配置加载ftl路径
        try {
            cfg.setDirectoryForTemplateLoading(new File("E:\\Java Project\\WEB_chat\\src\\main\\webapp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //配置页面编码
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        sce.getServletContext().setAttribute(TEMPLATE_KEY,cfg);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
