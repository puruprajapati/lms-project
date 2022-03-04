package com.lms.ui;

import com.lms.business.AuthenticationService;
import com.lms.model.Login;

import java.io.Console;
import java.io.IOException;

import static com.lms.Main.bufferedReader;

public class LoginUI {

    public  static Boolean displayLogin(){
        AuthenticationService authService = AuthenticationService.getInstance();
        Login userCredential = new Login();
        Console cm1 = System.console();
        try {
            System.out.println("Enter username: ");
            userCredential.setUsername(bufferedReader.readLine());
            System.out.println("Enter password: ");
            userCredential.setPassword(bufferedReader.readLine());
            if (cm1 == null) {
                System.out.println(
                        "No console available");
            }
            // Read password
            // into character array
            char[] ch = cm1.readPassword(
                    "Enter password : ");

            // Print password
            System.out.println(
                    "Password : " + ch);


        }catch(IOException e){
            e.getMessage();

        }
        return authService.login(userCredential);

    }

    public  static void displayInvalidCredential(){
        System.out.println("Invalid username or password!!!");
    }

}
