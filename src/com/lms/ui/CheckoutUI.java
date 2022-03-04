package com.lms.ui;

import com.lms.business.BookService;
import com.lms.business.CheckoutService;
import com.lms.model.Book;
import com.lms.model.CheckoutEntity;

import java.io.IOException;
import java.util.List;

import static com.lms.Main.bufferedReader;

public class CheckoutUI {
    public static void checkoutBook() throws IOException {
        System.out.println("Creating Checkout Records");
        System.out.println("Enter Entry Id:");
        String entryId = bufferedReader.readLine();
        System.out.println("Enter Member Id :");
        String memberId = bufferedReader.readLine();
        System.out.println("Enter Book ISBN :");
        String isbn = bufferedReader.readLine();

        String addBookResult = CheckoutService.getInstance().createNewCheckout(entryId, memberId, isbn);

        DashboardUI.enterKey(addBookResult);
    }

    public static void getCheckoutEntries() throws IOException {
        System.out.println("List of checkout entries are:");
        List<CheckoutEntity> entires = CheckoutService.getInstance().getAllCheckoutEntries();
        for(CheckoutEntity entry: entires)
            System.out.println("Book ISBN : " + entry.getBookCopy().getBook().getIsbn() + ", Book Title: " + entry.getBookCopy().getBook().getTitle() + ", Member ID: " + entry.getMemberId() + ", Member Name: " + entry.getMember().getFirstName() + " " + entry.getMember().getLastName() + ", Borrowed Date: " + entry.getBorrowedDate() + ", Return Date: " + entry.getReturnDate());

        DashboardUI.enterKey("");


    }

    public static void getCheckoutEntryByLibraryMemberId() throws IOException{
        System.out.println("List of checkout entries by Member are:");
        System.out.println("Enter Member Id: ");
        String memberId = bufferedReader.readLine();
        List<CheckoutEntity> entries = CheckoutService.getInstance().getCheckoutEntryByLibraryMemberId(memberId);
        if(entries == null || entries.size() == 0){
            DashboardUI.enterKey("Invalid Member Id");
        }
        else {
            for (CheckoutEntity entry : entries)
                System.out.println("Book ISBN : " + entry.getBookCopy().getBook().getIsbn() + ", Book Title: " + entry.getBookCopy().getBook().getTitle() + ", Member ID: " + entry.getMemberId() + ", Member Name: " + entry.getMember().getFirstName() + " " + entry.getMember().getLastName() + ", Borrowed Date: " + entry.getBorrowedDate() + ", Return Date: " + entry.getReturnDate());

            DashboardUI.enterKey("");
        }
    }
}
