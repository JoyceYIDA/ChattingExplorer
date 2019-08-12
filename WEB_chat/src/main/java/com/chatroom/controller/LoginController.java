package com.chatroom.controller;

import com.chatroom.Util.CommUtils;
import com.chatroom.config.FreeMarkerListener;
import com.chatroom.service.AccountService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yida
 */
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    AccountService accountService=new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        resp.setContentType("text/html;charset=utf8");
        PrintWriter pout=resp.getWriter();
        if(CommUtils.strIsNull(userName)||CommUtils.strIsNull(password)){
            //登陆失败，停留在登录页面
            pout.println("    <script>\n" +
                    "        alert(\"用户名或密码为空!\");\n" +
                    "        window.location.href = \"/index.html\";\n" +
                    "    </script>");
        }else if(accountService.userLogin(userName,password)){
            //登陆成功，进入聊天页面（加载chat.ftl)
            Template template=getTemplate(req,"/chat.ftl");
            Map<String,String> map=new HashMap<>();
            map.put("username",userName);
            try {
                template.process(map,pout);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }else{
            //登陆失败，密码与用户名不匹配，停留在登录页面
            pout.println("    <script>\n" +
                    "        alert(\"用户名或密码不正确!\");\n" +
                    "        window.location.href = \"/index.html\";\n" +
                    "    </script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private Template getTemplate(HttpServletRequest req,String fileName){
        Configuration cfg= (Configuration) req.getServletContext()
                .getAttribute(FreeMarkerListener.TEMPLATE_KEY);
        try {
            return cfg.getTemplate(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
