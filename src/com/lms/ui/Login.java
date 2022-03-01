package com.lms.ui;

import com.lms.business.Authentication;
import java.io.IOException;

import static com.lms.Main.bufferedReader;

public class Login {

    public  static Boolean displayLogin(){
        Authentication authService = Authentication.getInstance();
        com.lms.model.Login userCredential = new com.lms.model.Login();
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
