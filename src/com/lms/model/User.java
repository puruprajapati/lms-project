package com.lms.model;

import com.lms.common.LmsConstant;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String password;
    private LmsConstant.Authorization authorization;

    public User(String id, String password, LmsConstant.Authorization authorization) {
        this.id = id;
        this.password = password;
        this.authorization=authorization;
    }

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public LmsConstant.Authorization getAuthorization() {
        return authorization;
    }
}
