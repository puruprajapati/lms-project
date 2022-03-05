package com.lms.ui;

import com.lms.business.BookService;
import com.lms.business.CheckoutService;
import com.lms.common.CommandLineTable;
import com.lms.model.Book;
import com.lms.model.CheckoutEntity;
import com.lms.rulesets.CommonRuleSet;
import com.lms.rulesets.RuleException;

import java.io.IOException;
import java.util.List;

import static com.lms.Main.bufferedReader;

public class CheckoutUI {
    public static void checkoutBook() throws IOException, RuleException {
        try {
            System.out.println("Creating Checkout Records");
            System.out.println("Enter Entry Id:");
            String entryId = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(entryId);
            System.out.println("Enter Member Id :");
            String memberId = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(memberId);
            System.out.println("Enter Book ISBN :");
            String isbn = bufferedReader.readLine();
            CommonRuleSet.isNullOrEmpty(isbn);


            String addBookResult = CheckoutService.getInstance().createNewCheckout(entryId, memberId, isbn);

            DashboardUI.enterKey(addBookResult);
        }catch(RuleException ex){
            System.out.println("WARNING: " + ex.getMessage());
            DashboardUI.enterKey("");
        }

    }

    public static void getCheckoutEntries() throws IOException {
        System.out.println("List of checkout entries are:");
        List<CheckoutEntity> entires = CheckoutService.getInstance().getAllCheckoutEntries();

        CommandLineTable visualData = new CommandLineTable();
        visualData.setShowVerticalLines(true);
        visualData.setHeaders("Book ISBN", "Book Title", "Member ID", "Member Name", "Burrowed Date", "Return Date");

        for(CheckoutEntity entry: entires)
            visualData.addRow(entry.getBookCopy().getBook().getIsbn(), entry.getBookCopy().getBook().getTitle(), entry.getMemberId(), entry.getMember().getFirstName() + " " + entry.getMember().getLastName(), String.valueOf(entry.getBorrowedDate()),  String.valueOf(entry.getReturnDate()));

        visualData.print();

        DashboardUI.enterKey("");


    }

    public static void getBooksWithExceedDueDate() throws IOException{
        System.out.println("Books crossing due date");
        List<CheckoutEntity> entries = CheckoutService.getInstance().getBooksWithExceedDueDate();
        CommandLineTable visualData = new CommandLineTable();
        visualData.setShowVerticalLines(true);
        visualData.setHeaders("Book ISBN", "Book Title", "Member ID", "Member Name", "Burrowed Date", "Return Date", "Due Date");

        for (CheckoutEntity entry : entries)
            visualData.addRow(entry.getBookCopy().getBook().getIsbn(), entry.getBookCopy().getBook().getTitle(), entry.getMemberId(), entry.getMember().getFirstName() + " "
                    + entry.getMember().getLastName(), String.valueOf(entry.getBorrowedDate()),  String.valueOf(entry.getReturnDate()), String.valueOf(entry.getDueDate()));

        visualData.print();

        DashboardUI.enterKey("");
    }

    public static void getCheckoutEntryByLibraryMemberId() throws IOException{
        System.out.println("List of checkout entries by Member are:");
        System.out.println("Enter Member Id: ");
        String memberId = bufferedReader.readLine();
        List<CheckoutEntity> entries = CheckoutService.getInstance().getCheckoutEntryByLibraryMemberId(memberId);
        if(entries == null || entries.size() == 0){
            DashboardUI.enterKey("No Records..");
        }
        else {
            CommandLineTable visualData = new CommandLineTable();
            visualData.setShowVerticalLines(true);
            visualData.setHeaders("Book ISBN", "Book Title", "Member ID", "Member Name", "Burrowed Date", "Return Date");

            for (CheckoutEntity entry : entries)
                visualData.addRow(entry.getBookCopy().getBook().getIsbn(), entry.getBookCopy().getBook().getTitle(), entry.getMemberId(), entry.getMember().getFirstName() + " " + entry.getMember().getLastName(), String.valueOf(entry.getBorrowedDate()),  String.valueOf(entry.getReturnDate()));

            visualData.print();

            DashboardUI.enterKey("");
        }
    }
}
