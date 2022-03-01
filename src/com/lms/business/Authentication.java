package com.lms.business;

import com.lms.model.Login;

public class Authentication {
    private static Authentication authIsntance = null;

    private Authentication(){};

    public static Authentication getInstance() {
        if (authIsntance == null) {
            authIsntance = new Authentication();
        }

        return authIsntance;
    }

    public Boolean login(Login loginModel){
        return true;
    }
}
