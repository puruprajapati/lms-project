package com.lms;

import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.model.Book;
import com.lms.ui.DashboardUI;
import com.lms.ui.LoginUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static InputStreamReader isr = new InputStreamReader(System.in);
    public static BufferedReader bufferedReader = new BufferedReader(isr);

    public static void main(String[] args) throws IOException {

        DataAccess da = new DataAccessFacade();

        List<Book> allBooks = new ArrayList<Book>() {
            {
                add(new Book("23-11451", "The Big Fish", 21));
                add(new Book("23-11452", "The Big Fish2", 21));
            }
        };

        System.out.println(allBooks);

        DataAccessFacade.loadBookMap(allBooks);

        List<Book> books = new ArrayList<>();
        HashMap<String, Book> b = da.readBooksMap();
        if (b != null) {
            Set<String> keys = b.keySet();
            for (String k : keys) {
                Book lb = b.get(k);
                books.add(lb);
            }
        }

        for(Book bk : books){
            System.out.println(bk.getIsbn());
            System.out.println(bk.getTitle());
        }


        da.clearBooks();

        //initApp();
    }

    public static void initApp() throws IOException {
        Boolean isValidated = LoginUI.displayLogin();
        if(isValidated){
            DashboardUI.displayDashboard();
            DashboardUI.enterOption();

            // run app until user closes it
            Boolean isExist = true;
            while(isExist){
                Integer selectedOption = Integer.valueOf(bufferedReader.readLine());
                DashboardUI.action(selectedOption);
            }
        } else {
            LoginUI.displayInvalidCredential();
            initApp();
        }
    }
}
