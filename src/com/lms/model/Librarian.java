

/**
 * @author maher
 * @version 1.0
 * @created 01-Mar-2022 12:00:03 PM
 */
public class Librarian {

	public Librarian(){

	}

	public void finalize() throws Throwable {

	}

}

/**
 * @author maher
 * @version 1.0
 * @updated 02-Mar-2022 11:05:35 AM
 */
public class Author extends <<Person>> {

	private String biography;
	public Book m_Book;

	public Author(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}