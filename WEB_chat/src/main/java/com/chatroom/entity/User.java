package com.chatroom.entity;

import lombok.Data;

/**
 * @author yida
 *
 */
@Data
public class User {
    private Integer ID;
    private String userName;
    private String password;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
