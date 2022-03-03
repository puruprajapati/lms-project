package com.lms.model;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 7508481940058530471L;

    private String credential;

    public Author(String firstName, String lastName, String telephoneNo, Address address, String credential) {
        super(firstName, lastName, telephoneNo, address);
        this.credential = credential;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    public String getBio() {
        return credential;
    }
}
