package com.lms.ui;

import com.lms.business.Authentication;
import com.lms.model.Login;

import java.io.IOException;

import static com.lms.Main.bufferedReader;

public class LoginUI {

    public  static Boolean displayLogin(){
        Authentication authService = Authentication.getInstance();
        Login userCredential = new Login();
        try {
            System.out.println("Enter username: ");
            userCredential.setUsername(bufferedReader.readLine());
            System.out.println("Enter password: ");
            userCredential.setUsername(bufferedReader.readLine());

        }catch(IOException e){
            e.getMessage();

        }
        return authService.login(userCredential);

    }

    public  static void displayInvalidCredential(){
        System.out.println("Invalid username or password!!!");
    }

}
