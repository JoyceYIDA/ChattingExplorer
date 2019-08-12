package com.chatroom.entity;

/**
 * @author yida
 */
public class MsgFromClient {
    //聊天信息
    private String msg;
    //聊天类别
    private String type;
    //私聊对象的SessionID
    private String to;

    public String getMsg() {
        return msg;
    }

    public String getType() {
        return type;
    }

    public String getTo() {
        return to;
    }
}
