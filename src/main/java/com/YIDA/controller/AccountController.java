package com.YIDA.controller;

import com.YIDA.service.AccountService;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yida
 * websocket协议：全双工协议 tcp/ip（应用层协议）
 *
 */
@WebServlet(urlPatterns = "/doRegister")
public class AccountController extends HttpServlet {
    private AccountService accountService=new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        resp.setContentType("test/html;charset=utf8");
        PrintWriter printWriter=resp.getWriter();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
