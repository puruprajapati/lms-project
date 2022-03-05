package com.lms;

import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.dataaccess.LoadData;
import com.lms.model.Book;
import com.lms.rulesets.CommonRuleSet;
import com.lms.rulesets.RuleException;
import com.lms.ui.DashboardUI;
import com.lms.ui.LoginUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static InputStreamReader isr = new InputStreamReader(System.in);
    public static BufferedReader bufferedReader = new BufferedReader(isr);

    public static void main(String[] args) throws IOException, RuleException {
        LoadData ld = new LoadData();
        ld.loadAllData();
        initApp();
    }

    public static void initApp() throws IOException, RuleException {
        try{
            Boolean isValidated = LoginUI.displayLogin();
            if(isValidated){
                appExecution();
            } else {
                LoginUI.displayInvalidCredential();
                initApp();
            }
        }catch(RuleException ex){
            appExecution();
        }

    }

    public static void appExecution() throws IOException, RuleException {
        try {
            DashboardUI.displayDashboard();
            DashboardUI.enterOption();

            // run app until user closes it
            Boolean isExist = true;
            while (isExist) {
                String option = bufferedReader.readLine();
                CommonRuleSet.isNumber(option);
                Integer selectedOption = Integer.valueOf(option);
                DashboardUI.action(selectedOption);
            }
        } catch (RuleException ex) {
            appExecution();

        }
    }

}
