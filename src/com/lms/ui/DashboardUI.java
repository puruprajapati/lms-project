package com.lms.ui;

import com.lms.Main;
import com.lms.common.LmsConstant.Authorization;
import com.lms.common.LmsUtility;
import com.lms.common.UserSession;
import com.lms.rulesets.RuleException;

import java.io.IOException;

import static com.lms.Main.bufferedReader;
import static com.lms.Main.main;

public class DashboardUI {
    public static void displayDashboard(){
        System.out.println("****************************************************************");
        System.out.println("               Welcome to Library Management System                   ");
        System.out.println("****************************************************************");
        System.out.println();
        System.out.println("Greeting, " + UserSession.getInstance().getUserName());
        System.out.println("You are logged in as a role of " + UserSession.getInstance().getAuthorization());
        System.out.println();

        mainMenu();

    }

    public static void enterOption(){
        System.out.println("********* Please Enter your Option *********");
    }

    public static void enterKey(String msg) throws IOException {
        System.out.println(msg);
        System.out.println();
        System.out.println("Please enter any key");
        bufferedReader.readLine();
    }

    public static void action(int selectedOption) throws IOException, RuleException {
        switch (selectedOption){
            case 1:
                MemberUI.addMember("Creating a new User");
                mainMenu();
                break;
            case 2:
                MemberUI.updateMember();
                mainMenu();
                break;
            case 3:
              MemberUI.listUser();
                mainMenu();
                break;
            case 4:
                MemberUI.deleteMember();
                mainMenu();
                break;
            case 5:
                BookUI.addBook("Creating new book:");
                mainMenu();
                break;
            case 6:
                BookUI.updateBook();
                mainMenu();
                break;
            case 7:
                BookUI.searchBook();
                mainMenu();
                break;
            case 8:
                BookUI.listBook();
                mainMenu();
                break;
            case 9:
                BookUI.addBookCopy();
                mainMenu();
                break;
            case 11:
                CheckoutUI.checkoutBook();
                mainMenu();
                break;
            case 12:
                CheckoutUI.getCheckoutEntries();
                mainMenu();
                break;
            case 13:
                CheckoutUI.getCheckoutEntryByLibraryMemberId();
                mainMenu();
                break;
            case 10:
                UserSession.destroySession();
                Main.initApp();
                break;
            case 0:
                System.out.println("Closing an Application ....");
                stopApplication();
            default:
                System.out.println("Option is Not Recognized!");
                mainMenu();
                break;

        }
    }

    public static void mainMenu() {
        if(UserSession.getInstance().getAuthorization() == Authorization.ADMIN ){
            mainMenuForAdmin();
        }else if( UserSession.getInstance().getAuthorization() ==Authorization.BOTH){
            mainMenuForBoth();
        } else {
            mainMenuForLibraryMember();
        }

    }

    private static void mainMenuForAdmin() {
        LmsUtility formatMenu = new LmsUtility();
        formatMenu
                .addLine("")
                .addLine("--Please select from below options--")
                .addLine(
                        "********** MAIN MENU **********")
                .addLine("Press '1' To Add Member")
                .addLine("Press '2' To Edit Member")
                .addLine("Press '3' To List Member")
                .addLine("Press '4' To Delete Member")
                .addLine("Press '5' To Add New Book")
                .addLine("Press '6' To Edit a Book")
                .addLine("Press '7' To Search a Book")
                .addLine("Press '8' To List All Book")
                .addLine("Press '9' To Make a Copy of Book")
                .addLine("Press '10' 'Logout'")
                .addLine("Press '0' 'Exit'");
        formatMenu.print();

    }

    private static void mainMenuForBoth() {
        LmsUtility formatMenu = new LmsUtility();
        formatMenu
                .addLine("")
                .addLine("--Please select from below options--")
                .addLine(
                        "********** MAIN MENU **********")
                .addLine("Press '1' To Add Member")
                .addLine("Press '2' To Edit Member")
                .addLine("Press '3' To List Member")
                .addLine("Press '4' To Delete Member")
                .addLine("Press '5' To Add New Book")
                .addLine("Press '6' To Edit a Book")
                .addLine("Press '7' To Search a Book")
                .addLine("Press '8' To List All Book")
                .addLine("Press '9' To Make a Copy of Book")
                .addLine("Press '11' To Checkout a Book")
                .addLine("Press '12' View All Checkout Record")
                .addLine("Press '13' View Checkout Record of a Member")
                .addLine("Press '10' 'Logout'")
                .addLine("Press '0' 'Exit'");
        formatMenu.print();

    }

    private static void mainMenuForLibraryMember() {
        LmsUtility formatMenu = new LmsUtility();
        formatMenu
                .addLine("")
                .addLine("--Please select from below options--")
                .addLine(
                        "********** MAIN MENU **********")
                .addLine("Press '3' To List Member")
                .addLine("Press '7' To Search a Book")
                .addLine("Press '8' To List All Book")
                .addLine("Press '11' To Checkout a Book")
                .addLine("Press '12' View All Checkout Record")
                .addLine("Press '13' View Checkout Record of a Member")
                .addLine("Press '10' 'Logout'")
                .addLine("Press '0' 'Exit'");
        formatMenu.print();

    }

    private static void  stopApplication(){
        // Call method to stop application
        System.exit(1);
    }


}
