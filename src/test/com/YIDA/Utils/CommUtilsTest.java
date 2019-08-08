package com.YIDA.Utils;

import com.YIDA.Utils.CommUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * 测试文件夹与main同目录
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
}