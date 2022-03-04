package com.lms.ui;

import com.lms.business.MemberService;
import com.lms.common.CommandLineTable;
import com.lms.model.Address;
import com.lms.model.LibraryMember;
import com.lms.model.Login;
import com.lms.rulesets.CommonRuleSet;
import com.lms.rulesets.RuleException;

import java.io.IOException;
import java.util.List;

import static com.lms.Main.bufferedReader;

public class MemberUI {
    public static void listUser() throws IOException {
        System.out.println("List of users are:");
        List<LibraryMember> memberList = MemberService.getInstance().getAllMembers();

        CommandLineTable visualData = new CommandLineTable();
        visualData.setShowVerticalLines(true);
        visualData.setHeaders("Member ID", "Member Name", "Phone Number", "Address");

        for(LibraryMember member: memberList)
            visualData.addRow(member.getMemberId(), member.getFirstName() + " " + member.getLastName(), member.getTelephone(), String.valueOf(member.getAddress()));

        visualData.print();

        DashboardUI.enterKey("");


    }

    public static void addMember(String addMemberMsg) throws IOException{
        System.out.println(addMemberMsg);
        try {
            System.out.println("Enter Member ID: ");
            String memberId = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(memberId);
            System.out.println("Enter First Name: ");
            String fname = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(fname);
            System.out.println("Enter Last Name: ");
            String lname = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(lname);
            System.out.println("Enter Phone No: ");
            String tel = bufferedReader.readLine();
            System.out.println("Enter Street: ");
            String street = bufferedReader.readLine();
            System.out.println("Enter City: ");
            String city = bufferedReader.readLine();
            System.out.println("Enter State: ");
            String state = bufferedReader.readLine();
            System.out.println("Enter Zip: ");
            String zipCode = bufferedReader.readLine();
            CommonRuleSet.isNumber(zipCode);
            int zip = Integer.parseInt(zipCode);
            LibraryMember lm = new LibraryMember(memberId, fname, lname, tel, new Address(street, city, state, zip));

            MemberService.getInstance().addNewMember(lm);

            DashboardUI.enterKey("Library Member Created Successfully!!");

        }catch(IOException e){
            e.getMessage();

        } catch (RuleException ex) {
            System.out.println("WARNING: " + ex.getMessage());
        }

        DashboardUI.enterKey("");
    }

    public static void deleteMember() throws IOException, RuleException {
        try {
            System.out.println("Enter Member ID to be Deleted: ");
            String memberId = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(memberId);
            boolean isDeleted = MemberService.getInstance().deleteMember(memberId);

            if (isDeleted)
                DashboardUI.enterKey("Library Member Removed Successfully!!");
            else
                DashboardUI.enterKey("Invalid Member Id!!");
        }catch (RuleException ex){
            System.out.println("WARNING: " + ex.getMessage());
        }

        DashboardUI.enterKey("");
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
