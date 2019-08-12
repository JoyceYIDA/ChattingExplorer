package com.chatroom.Util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author yida
 */
public class CommUtilsTest {

    @Test
    public void loadProperties(){
        String fileName="db.properties";
        Properties properties=CommUtils.loadProperties(fileName);
        String url=properties.getProperty("url");
        //添加断言
        Assert.assertNotNull(url);
    }

    @Test
    public void object2Json() {
    }

    @Test
    public void json2Object() {
    }

    @Test
    public void strIsNull() {
    }
}