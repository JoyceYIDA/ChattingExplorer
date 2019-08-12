package com.YIDA.Utils;

import com.chatroom.entity.User;
import org.junit.Test;

/**
 * 测试文件夹与main同目录
 */
public class CommUtilsTest {

    @Test
    public void gsonTest1(){
        User user=new User();
        user.setID(10);
        user.setUserName("test2");
        user.setPassword("123");
        String jsonStr=CommUtils.object2Json(user);
        System.out.println(jsonStr);
    }

    @Test
    public void gsonTest2(){
        String jsonStr="{\"ID\":10,\"userName\":\"test2\",\"password\":\"123\"}";
        User user= (User) CommUtils.json2Object(jsonStr,User.class);
        System.out.println(user);
    }
}