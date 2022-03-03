package com.lms.ui;

import com.lms.business.MemberService;
import com.lms.model.Address;
import com.lms.model.LibraryMember;
import com.lms.model.Login;

import java.io.IOException;
import java.util.List;

import static com.lms.Main.bufferedReader;

public class MemberUI {
    public static void listUser() throws IOException {
        System.out.println("List of users are:");
        List<LibraryMember> memberList = MemberService.getInstance().getAllMembers();
        for(LibraryMember member: memberList)
            System.out.println(member);

        DashboardUI.enterKey("");


    }

    public static void addMember(String addMemberMsg) throws IOException{
        System.out.println(addMemberMsg);
        try {
            System.out.println("Enter Member ID: ");
            String memberId = bufferedReader.readLine();
            System.out.println("Enter First Name: ");
            String fname = bufferedReader.readLine();
            System.out.println("Enter Last Name: ");
            String lname = bufferedReader.readLine();
            System.out.println("Enter Phone No: ");
            String tel = bufferedReader.readLine();
            System.out.println("Enter Street: ");
            String street = bufferedReader.readLine();
            System.out.println("Enter City: ");
            String city = bufferedReader.readLine();
            System.out.println("Enter State: ");
            String state = bufferedReader.readLine();
            System.out.println("Enter Zip: ");
            int zip = Integer.parseInt(bufferedReader.readLine());
            LibraryMember lm = new LibraryMember(memberId, fname, lname, tel, new Address(street, city, state, zip));

            MemberService.getInstance().addNewMember(lm);

            DashboardUI.enterKey("Library Member Created Successfully!!");

        }catch(IOException e){
            e.getMessage();

        }
    }

    public static void deleteMember() throws IOException {
        System.out.println("Enter Member ID to be Deleted: ");
        String memberId = bufferedReader.readLine();
        boolean isDeleted = MemberService.getInstance().deleteMember(memberId);

        if (isDeleted)
            DashboardUI.enterKey("Library Member Removed Successfully!!");
        else
            DashboardUI.enterKey("Invalid Member Id!!");
    }


    public static void updateMember() throws IOException {
        System.out.println("Enter Member ID to Update: ");
        String memberId = bufferedReader.readLine();
        boolean isDeleted = MemberService.getInstance().deleteMember(memberId);
        if(isDeleted){
            addMember("Enter updated Info for Member");
        }else
            DashboardUI.enterKey("Invalid Member Id!!");
    }

}
