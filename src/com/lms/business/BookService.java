package com.lms.business;

import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.model.Author;
import com.lms.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BookService {
    private static BookService bookInstance = null;
    DataAccess da = new DataAccessFacade();

    private BookService(){};

    public static BookService getInstance() {
        if (bookInstance == null) {
            bookInstance = new BookService();
        }
        return bookInstance;
    }

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        HashMap<String, Book> b = da.readBooksMap();
        if (b != null) {
            Set<String> keys = b.keySet();
            for (String k : keys) {
                Book lb = b.get(k);
                books.add(lb);
            }
        }

        return books;
    }

    public void addNewBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        Book book = new Book(isbn, title, maxCheckoutLength, authors);
        da.saveNewBook(book);
    }

    public Book searchBook(String isbn) {
        Book book = null;
        HashMap<String, Book> books = da.readBooksMap();
        if (!(books.containsKey(isbn))) {
            return null;
        }
        Set<String> keys = books.keySet();
        for (String k : keys) {
            if (k.equals(isbn)) {
                book = books.get(k);
            }
        }
        return book;
    }

    public void addBookCopy(Book book) {
        Book newBook = book;
        newBook.addCopy();
        HashMap<String, Book> bookHashMap = da.readBooksMap();
        for (String isbn : bookHashMap.keySet()) {
            if (isbn.equals(newBook.getIsbn())) {
                Book oldBook = bookHashMap.get(isbn);
                bookHashMap.replace(isbn, oldBook, newBook);
            }
        }
        da.clearBooks();
        da.loadBooks(bookHashMap);
    }

    public void updateBook(Book book) {
        HashMap<String, Book> bookHashMap = da.readBooksMap();
        for (String isbn : bookHashMap.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                Book oldBook = bookHashMap.get(isbn);
                bookHashMap.replace(isbn, oldBook, book);
                da.loadBooks(bookHashMap);
                break;
            }
        }
    }
}
