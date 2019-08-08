package com.YIDA.service;

import com.YIDA.Utils.CommUtils;
import com.YIDA.entity.MsgFromClient;
import com.YIDA.entity.MsgToClient;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yida
 */
@ServerEndpoint("/websocket")
public class WebSocket {
    private static CopyOnWriteArraySet<WebSocket> clients=
            new CopyOnWriteArraySet<>();
    private Session session;
    //缓存所有用户列表和当前用户名
    private static Map<String,String> names=new ConcurrentHashMap<>();
    private String userName;
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        String userName=session.getQueryString().split("=")[1];
        this.userName=userName;
        clients.add(this);//将当前客户端聊天实体保存到clients中
        names.put(session.getId(),userName);//将当前用户以及sessionID保存到用户列表中

        System.out.println("有新的连接，当前sessionID为"+session.getId()
        +"用户名为"+userName);
        MsgToClient msgToClient=new MsgToClient();
        msgToClient.setContent(userName+"上线了");
        msgToClient.setNames(names);
        String jsonStr=CommUtils.object2Json(msgToClient);
        for(WebSocket webSocket:clients){
            webSocket.sendMsg(jsonStr);
        }
    }
    @OnMessage
    public void onMessage(String msg){
        MsgFromClient msgFromClient=(MsgFromClient)
                CommUtils.json2Object(msg,MsgFromClient.class);
        if(msgFromClient.getType().equals("1")){
            //群聊
            String content=msgFromClient.getMsg();
            MsgToClient msgToClient=new MsgToClient();
            msgToClient.setContent(content);
            msgToClient.setNames(names);
            //每一个都发送
            for(WebSocket webSocket:clients){
                webSocket.sendMsg(CommUtils.object2Json(msgToClient));
            }
        }else if(msgFromClient.getType().equals("2")){
            //私聊  {"to":"0-1-2-","msg","1234567","type":2}
            String content=msgFromClient.getMsg();
            int toL=msgFromClient.getTo().length();
            String[] tos=msgFromClient.getTo().substring(0,toL-1).split("-");
            List<String> list= Arrays.asList(tos);
            //给指定的sessionID发消息
            for(WebSocket webSocket:clients){
                if(list.contains(webSocket.session.getId())&&
                        this.session.getId()!=webSocket.session.getId()){
                    //发送私聊消息
                    MsgToClient msgToClient=new MsgToClient();
                    msgToClient.setContent(content);
                    msgToClient.setNames(names);
                    webSocket.sendMsg(CommUtils.object2Json(msgToClient));
                }
            }
        }
    }
    public void sendMsg(String msg){
        try {
            this.session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onClose(){
        //将当前客户端实体移除
        clients.remove(this);
        names.remove(session.getId());//将当前用户sessionID移除

        System.out.println("有连接下线了，用户名为"+userName);
        MsgToClient msgToClient=new MsgToClient();
        msgToClient.setContent(userName+"下线了");
        msgToClient.setNames(names);
        String jsonStr=CommUtils.object2Json(msgToClient);
        for(WebSocket webSocket:clients){
            webSocket.sendMsg(jsonStr);
        }
    }
    @OnError
    public void OnError(Throwable e){
        System.out.println("连接失败");
        e.printStackTrace();
    }
}
