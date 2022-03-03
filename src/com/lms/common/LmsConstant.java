package com.lms.common;

import java.io.Serializable;

public interface LmsConstant {
    public String LMS_COMMA = ",";
    public String LMS_LINECHANGE = "\n";
    public String LMS_TABSPACE = "\t";
    public String LMS_BLANKSTRING="";
    public String LMS_SPACESTRING=" ";
    public String LMS_BOOKID="BookID";
    public String LMS_AUTHOR = "Author";
    public String LMS_TITLE = "Title";
    public String LMS_LIBRARYID = "LibraryID";
    public String LMS_LibraryName = "Library Name";
    public String LMS_PUBLISHER = "Publisher";

    public enum Authorization implements Serializable {
        LIBRARIAN, ADMIN, BOTH;
    }



}
