package com.lms.model;

import java.io.Serializable;
import java.util.*;

final public class Book implements Serializable {

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