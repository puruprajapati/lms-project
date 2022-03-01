package com.lms.ui;

import com.lms.common.LmsUtility;

import java.io.IOException;

public class Dashboard {
    public static void displayDashboard(){
        System.out.println("****************************************************************");
        System.out.println("               Welcome to Library Mgmt System                   ");
        System.out.println("****************************************************************");
        System.out.println("--Please select from below options--");
        mainMenu();
    }

    public static void enterOption(){
        System.out.println("********* Please Enter your Option *********");
    }

    public static void action(int selectedOption) throws IOException {
        switch (selectedOption){
            case 0:
                // function call
                mainMenu();
                break;
            case 1:
                // function call
                mainMenu();
                break;
            case 2:
                // function call
                mainMenu();
                break;
            case 8:
                System.out.println("Closing an Application ....");
                stopApplication();
            default:
                System.out.println("Option is Not Recognized!");
                mainMenu();
                break;

        }
    }


    private static void mainMenu() {
        //Instantiate FormattedColumns Object to print line
        LmsUtility formatMenu = new LmsUtility();
        //Add lines of record
        formatMenu
                .addLine(
                        "********** MAIN MENU **********")
                .addLine("Press '0' To Add User")
                .addLine("Press '1' To Add Book")
                .addLine("Press '5' To Add New Book")
                .addLine("Press '6' Issue a Book")
                .addLine("Press '7' Delete a Book")
                .addLine("Press '8' 'Exit'");
        //print the output
        formatMenu.print();

    }

    private static void  stopApplication(){
        // Call method to stop application
        System.exit(1);
    }


}
