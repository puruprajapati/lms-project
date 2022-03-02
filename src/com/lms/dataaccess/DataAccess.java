package com.lms.dataaccess;

import java.util.HashMap;

import com.lms.model.*;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	void loadBooks(HashMap<String, Book> bookList);
	void saveNewBook(Book book);
	public void clearBooks();


}
