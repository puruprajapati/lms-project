

/**
 * @author maher
 * @version 1.0
 * @created 01-Mar-2022 12:00:03 PM
 */
public class LibraryMember extends <<Person>> {
	private int memberId;
	public CheckOutRecord m_CheckOutRecord;
	public UserRole m_UserRole;
	public Account m_Account;


	public LibraryMember(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param copyNum
	 * @param ISBN
	 */
	public boolean addBookCopy(int copyNum, String ISBN){
		return false;
	}

	/**
	 * 
	 * @param zip
	 * @param state
	 * @param city
	 * @param street
	 * @param Lname
	 * @param Fname
	 * @param id
	 */
	public boolean addNewLibraryMember(int zip, String state, String city, String street, String Lname, String Fname, int id){
		return false;
	}

	/**
	 * 
	 * @param ISBIN
	 * @param id
	 */
	public boolean checkoutBook(String ISBIN, int id){
		return false;
	}

	/**
	 * 
	 * @param password
	 * @param userId
	 */
	public boolean loginAsAdmin(String password, int userId){
		return false;
	}

	/**
	 * 
	 * @param password
	 * @param userId
	 */
	public boolean loginAsLibrarian(String password, int userId){
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public void printCheckoutRecord(int id){

	}

}