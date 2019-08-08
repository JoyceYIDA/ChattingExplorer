package com.YIDA.entity;

import java.util.Map;

/**
 * @author yida
 */
public class MsgToClient {

    //用户列表
    private Map<String,String> names;
    //聊天内容
    private String content;

    public void setContent(String content) {
        this.content = content;
    }
    public void setContent(String userName,String msg){
        this.content=userName+"说"+msg;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }
}
