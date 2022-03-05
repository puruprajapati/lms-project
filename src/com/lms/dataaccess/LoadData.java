package com.lms.dataaccess;

import com.lms.common.LmsConstant.*;
import com.lms.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadData {
    List<LibraryMember> members = new ArrayList<LibraryMember>();

    List<Address> addresses = new ArrayList<Address>() {
        {
            add(new Address("101 S. Main", "Fairfield", "IA", 52556));
            add(new Address("51 S. George", "Georgetown", "MI", 65434));
            add(new Address("23 Headley Ave", "Seville", "Georgia", 41234));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", 33556));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", 93736));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", 94301));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", 52556));
            add(new Address("501 Central", "Mountain View", "CA", 94707));
        }
    };

    public List<Author> allAuthors = new ArrayList<Author>() {
        {
            add(new Author("Iron", "Man", "641-819-2123", addresses.get(0), "A happy man is he."));
            add(new Author("Icredible", "Hulk", "641-819-2123", addresses.get(0), "A happy wife is she."));
            add(new Author("Aqua", "Man", "641-819-3223", addresses.get(1), "Thinker of thoughts."));
            add(new Author("Bat", "Man", "641-819-2232", addresses.get(2), "Author of childrens' books."));
            add(new Author("Super", "Man", "641-819-2663", addresses.get(3), "Known for her clever style."));
            add(new Author("Captain", "America", "641-819-3223", addresses.get(1), "Thinker of thoughts."));
            add(new Author("Black", "Widow", "641-819-2232", addresses.get(2), "Author of childrens' books."));
            add(new Author("The", "Flash", "641-819-2663", addresses.get(3), "Known for her clever style."));
        }
    };

    List<Book> allBooks = new ArrayList<Book>() {
        {
            add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
            add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
            add(new Book("11-11111", "The Da Vinchi Code", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("11-12331", "Digital Fortress", 7, Arrays.asList(allAuthors.get(2))));
            add(new Book("11-22223", "Inception", 21, Arrays.asList(allAuthors.get(3))));
            add(new Book("11-56882", "Angels and Demon", 7, Arrays.asList(allAuthors.get(4))));
        }
    };

    List<CheckoutEntity> allCheckoutEntries = new ArrayList<CheckoutEntity>(){
        {
            add(new CheckoutEntity("ent-1", "1000", LocalDate.now(), null, LocalDate.now().plusDays(7), allBooks.get(0).getCopies()[0], 0.0, null, 0,
                    new LibraryMember("1000", "Andy", "Rogers", "641-223-2211", addresses.get(4))));
        }
    };

    List<CheckoutRecord> allCheckoutRecords = new ArrayList<CheckoutRecord>() {
        {
            add(new CheckoutRecord("1001", new LibraryMember("100", "Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1002", new LibraryMember("101", "Ruth", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1003", new LibraryMember("102", "Heinz", "Kurz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1004", new LibraryMember("103", "Cornelia", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1005", new LibraryMember("104", "Werner", "Meyer", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1006", new LibraryMember("105", "Lydia", "Kunz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1007", new LibraryMember("106", "Anna", "Best", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1008", new LibraryMember("107", "Stefan", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1009", new LibraryMember("108", "Martin", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        }
    };

    public void loadCheckoutData(){
        DataAccessFacade.loadCheckoutRecordMap(allCheckoutRecords);
        DataAccessFacade.loadCheckoutEntityMap(allCheckoutEntries);
    }

    public void loadBookData() {
        allBooks.get(0).addCopy();
        allBooks.get(0).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(3).addCopy();
        allBooks.get(2).addCopy();
        allBooks.get(2).addCopy();
        DataAccessFacade.loadBookMap(allBooks);
    }

   public void loadLibraryMember() {
        LibraryMember libraryMember = new LibraryMember("1000", "Andy", "Rogers", "641-223-2211", addresses.get(4));
        members.add(libraryMember);
        libraryMember = new LibraryMember("1001", "Drew", "Stevens", "702-998-2414", addresses.get(5));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1002", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1003", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
        members.add(libraryMember);

        DataAccessFacade.loadMemberMap(members);
    }

    public void loadUserData() {
        User firstMember = new User("lib1", "lib1", Authorization.LIBRARIAN);
        User secondMember = new User("lib2", "lib3", Authorization.LIBRARIAN);
        User admin = new User("admin", "admin", Authorization.ADMIN);
        User superAdmin = new User("superadmin", "superadmin", Authorization.BOTH);
        DataAccessFacade.loadUserMap(new ArrayList<>(Arrays.asList(firstMember, secondMember, admin, superAdmin)));
    }

    public void loadAllData(){
        loadUserData();
        loadLibraryMember();
        loadBookData();
        loadCheckoutData();
    }
}
