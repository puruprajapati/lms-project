package com.lms.business;

import com.lms.common.UserSession;
import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AuthenticationService {
    private static AuthenticationService authIsntance = null;

    private AuthenticationService(){};

    public static AuthenticationService getInstance() {
        if (authIsntance == null) {
            authIsntance = new AuthenticationService();
        }

        return authIsntance;
    }

    public Boolean login(Login loginModel){
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();


        List<User> users = new ArrayList<>();

        if (map != null) {
            Set<String> keys = map.keySet();
            for (String k : keys) {
                User u = map.get(k);
                users.add(u);
            }
        }

        if (!map.containsKey(loginModel.getUsername())) {
            return false;
        }
        String passwordFound = map.get(loginModel.getUsername()).getPassword();
        if (!passwordFound.equals(loginModel.getPassword())) {
            return false;
        }
        UserSession.createInstance(map.get(loginModel.getUsername()).getId(), map.get(loginModel.getUsername()).getAuthorization());
        return true;
    }
}
