package com.lms;

import com.lms.ui.Dashboard;
import com.lms.ui.Login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static InputStreamReader isr = new InputStreamReader(System.in);
    public static BufferedReader bufferedReader = new BufferedReader(isr);

    public static void main(String[] args) throws IOException {
        initApp();
    }

    public static void initApp() throws IOException {
        Boolean isValidated = Login.displayLogin();
        if(isValidated){
            Dashboard.displayDashboard();
            Dashboard.enterOption();

            // run app until user closes it
            Boolean isExist = true;
            while(isExist){
                Integer selectedOption = Integer.valueOf(bufferedReader.readLine());
                Dashboard.action(selectedOption);
            }
        } else {
            Login.displayInvalidCredential();
            initApp();
        }
    }
}
