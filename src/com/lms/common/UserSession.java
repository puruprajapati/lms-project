package com.lms.common;

import com.lms.common.LmsConstant.Authorization;

public class UserSession {
    private static UserSession instance;
    private String userName;
    private Authorization authorization;

    private UserSession(String userName, LmsConstant.Authorization authorization) {
        this.userName = userName;
        this.authorization = authorization;
    }


    public static UserSession createInstance(String userName, Authorization authorization) {
        if (instance == null) {
            instance = new UserSession(userName, authorization);
        }
        return instance;
    }

    public static void destroySession() {
        instance = null;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public String getUserName() {
        return userName;
    }

}
