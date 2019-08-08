package com.YIDA.service;

import com.YIDA.dao.AccountDao;
import com.YIDA.entity.User;

/**
 * @author yida
 */
public class AccountService {
    private AccountDao accountDao=new AccountDao();
    //用户登录
    public boolean userLogin(String name,String password){
        User user=new User();
        if (user != null) {
            return false;
        }
        return true;
    }
    public boolean userRegister(String username,String password){
        User user=new User();
        user.setUserName(username);
        user.setPassword(password);
        return accountDao.userRegister(user);
    }
}
