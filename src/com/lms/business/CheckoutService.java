package com.lms.business;

import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.model.Book;
import com.lms.model.BookCopy;
import com.lms.model.CheckoutEntity;
import com.lms.model.LibraryMember;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckoutService {
    private static CheckoutService checkOutInstance = null;
    DataAccess da = new DataAccessFacade();

    private CheckoutService(){};

    public static CheckoutService getInstance() {
        if (checkOutInstance == null) {
            checkOutInstance = new CheckoutService();
        }
        return checkOutInstance;
    }

    public String createNewCheckout(String entryId, String memberId, String isbn){
        LocalDate borrowedDate = LocalDate.now();
        LocalDate dueDate = null;
        LocalDate returnDate = null;
        double fAmount = 0.0;
        LocalDate pDate = null;
        long odue = 0;

        LibraryMember member = MemberService.getInstance().getMember(memberId);
        if(member == null){
            return  "Invalid memberId";
        }
        Book book = BookService.getInstance().searchBook(isbn);
        BookCopy bookCopy = null;
        if(book == null){
            return "Invalid ISBN number";
        }
        for (int i = 0; i < book.getCopies().length; i++) {
            if (book.getCopies()[i].isAvailable()) {
                bookCopy = book.getCopies()[i];
                break;
            }
        }

        if(bookCopy==null)
            return "No book copy found for this book.";

        returnDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());
        CheckoutEntity entity = new CheckoutEntity(entryId, memberId, borrowedDate, dueDate, returnDate, bookCopy, fAmount, pDate, odue, member);
        addNewCheckOut(entity);
        return "Checkout record Added successfully";
    }

    public void addNewCheckOut(CheckoutEntity entity){

        entity.getBookCopy().changeAvailability();
        BookService.getInstance().updateBook(entity.getBookCopy().getBook());
        da.saveNewCheckoutEntity(entity);
    }

    public List<CheckoutEntity> getAllCheckoutEntries(){
        List<CheckoutEntity> checkoutEntryList = new ArrayList<>();
        HashMap<String, CheckoutEntity> b = da.readCheckoutEntityMap();
        if (b != null) {
            Set<String> keys = b.keySet();
            for (String k : keys) {
                CheckoutEntity ce = b.get(k);
                checkoutEntryList.add(ce);
            }
        }

        return checkoutEntryList;
    }

    public List<CheckoutEntity> getCheckoutEntryByLibraryMemberId(String memberId){
        List<CheckoutEntity> checkoutEntryList = getAllCheckoutEntries();
        LibraryMember member = MemberService.getInstance().getMember(memberId);
        if(member == null) return null;

        checkoutEntryList = checkoutEntryList.stream().filter(ce -> memberId.equals(ce.getMemberId())).collect(Collectors.toList());

        return checkoutEntryList;

    }
}
