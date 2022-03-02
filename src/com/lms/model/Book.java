package com.lms.model;

/**
 * @author maher
 * @version 1.0
 * @created 01-Mar-2022 12:00:02 PM
 */
public class Book {

	/*
	private int avilableCopies;
	private String ISBN;
	private int maxCheckoutDays;
	private String title;
	public BookCopy m_BookCopy;


	public Book(){

	}

	public void finalize() throws Throwable {

	}

	public boolean addNewCopy(int copyNum){
		return false;
	}

	public boolean checkAvailability(){
		return false;
	}

	*/
	private static final long serialVersionUID = 6110690276685962829L;
	private final String isbn;
	private final String title;
	private final int maxCheckoutLength;
	public Book(String isbn, String title, int maxCheckoutLength) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle(){
		return title;
	}



}