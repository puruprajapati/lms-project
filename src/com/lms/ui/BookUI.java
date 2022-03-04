package com.lms.ui;

import com.lms.business.BookService;
import com.lms.business.MemberService;
import com.lms.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.lms.Main.bufferedReader;

public class BookUI {
    public static void listBook() throws IOException {
        System.out.println("List of books are:");
        List<Book> books = BookService.getInstance().getAllBooks();
        for(Book book: books)
            System.out.println(book);

        DashboardUI.enterKey("");


    }

    public static void addBook(String createBookMsg) throws IOException {
        Book bk = addBookFunction(createBookMsg);
        BookService.getInstance().addNewBook(bk.getIsbn(), bk.getTitle(), bk.getMaxCheckoutLength(), bk.getAuthors());

        DashboardUI.enterKey("New Book Added Successfully");
    }

    public static Book addBookFunction(String createBookMsg) throws IOException {
        List<Author> authors = new ArrayList<>();
        System.out.println(createBookMsg);
        System.out.println("Enter Book ISBN:");
        String isbn = bufferedReader.readLine();
        System.out.println("Enter Book Title:");
        String title = bufferedReader.readLine();
        System.out.println("Enter Checkout Length in Days:");
        int maxCheckoutLength = Integer.parseInt(bufferedReader.readLine());
        // add Author until user press esc key
        boolean exit = false;
        while(!exit){
            System.out.println("Enter Author First Name:");
            String authorFname = bufferedReader.readLine();
            System.out.println("Enter Author Last Name:");
            String authorLname = bufferedReader.readLine();
            System.out.println("Enter Author Telephone Number:");
            String authorTelNo = bufferedReader.readLine();
            System.out.println("Enter Author Credential:");
            String authorCredential = bufferedReader.readLine();
            System.out.println("Enter Author Street: ");
            String street = bufferedReader.readLine();
            System.out.println("Enter Author City: ");
            String city = bufferedReader.readLine();
            System.out.println("Enter Author State: ");
            String state = bufferedReader.readLine();
            System.out.println("Enter Author Zip: ");
            int zip = Integer.parseInt(bufferedReader.readLine());
            Author author = new Author(authorFname, authorLname, authorTelNo, new Address(street, city, state, zip), authorCredential);
            authors.add(author);
            System.out.println("If you want to enter next author, press '1', if not press '0'");
            String input = bufferedReader.readLine();
            if("0".equals(input)){
                exit = true;
            }



        }

        return new Book(isbn, title, maxCheckoutLength, authors);

    }

    public static void searchBook() throws IOException{
        System.out.println("Please Enter ISBN to Search Book");
        System.out.println("ISBN: ");
        String isbn = bufferedReader.readLine();
        Book book = BookService.getInstance().searchBook(isbn);

        if(book != null){
            DashboardUI.enterKey(book.toString());
        } else {
            DashboardUI.enterKey("Book not found");
        }
    }

    public static void updateBook() throws IOException{
        Book bk = addBookFunction("Enter information to update book");
        BookService.getInstance().updateBook(bk);

        DashboardUI.enterKey("Book Updated Successfully");
    }

    public static void addBookCopy() throws IOException{
        Book bk = addBookFunction("Enter information of a book to be copied");
        BookService.getInstance().addBookCopy(bk);

        DashboardUI.enterKey("Added Copy of Book Successfully");
    }

}
