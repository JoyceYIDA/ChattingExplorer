package com.YIDA.entity;

/**
 * @author yida
 */
public class User {
    private static int ID;
    private static String userName;
    private static String password;

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        User.ID = ID;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
