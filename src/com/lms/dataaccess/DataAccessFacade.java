package com.lms.dataaccess;

import com.lms.model.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DataAccessFacade implements DataAccess {

	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUT_RECORD, CHECKOUT_ENTITY
	}

	public static final String OUTPUT_DIR = "C:/pprajapati/Projects/lms/src/com/lms/dataaccess/storage";

	public static final String DATE_PATTERN = "MM/dd/yyyy";


	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	public void loadBooks(HashMap<String, Book> bookList) {
		saveToStorage(StorageType.BOOKS, bookList);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}

	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	public void clearMembers() {
		try {
			new FileOutputStream("StorageType.MEMBERS").close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMembers(List<LibraryMember> memberList) {
		loadMemberMap(memberList);
	}

	public boolean deleteMember(String memberId){
		HashMap<String, LibraryMember> mems = readMemberMap();

		Iterator<Map.Entry<String, LibraryMember>> itr = mems.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String, LibraryMember> entry = itr.next();
			if(memberId.equals(entry.getKey())){
				System.out.println("inside");
				itr.remove();
				saveToStorage(StorageType.MEMBERS, mems);
				return true;
			}
		}
		return false;
	}


	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}


	// CHECKOUT RECORDS
	static void loadCheckoutRecordMap(List<CheckoutRecord> checkoutRecordList) {
		HashMap<String, CheckoutRecord> recordHashMap = new HashMap<String, CheckoutRecord>();
		checkoutRecordList.forEach(checkoutRecord -> recordHashMap.put(checkoutRecord.getCheckoutId(), checkoutRecord));
		saveToStorage(StorageType.CHECKOUT_RECORD, recordHashMap);
	}

	// CHECKOUT ENTITY
	static void loadCheckoutEntityMap(List<CheckoutEntity> checkoutEntityList) {
		HashMap<String, CheckoutEntity> entityHashMap = new HashMap<String, CheckoutEntity>();
		checkoutEntityList.forEach(checkoutEntity -> entityHashMap.put(checkoutEntity.getEntryId(), checkoutEntity));
		saveToStorage(StorageType.CHECKOUT_ENTITY, entityHashMap);
	}




	@Override
	public void loadCheckoutRecords(HashMap<String, CheckoutRecord> recordHashMap) {
		saveToStorage(StorageType.CHECKOUT_RECORD, recordHashMap);
	}

	@Override
	public void loadCheckoutEntities(HashMap<String, CheckoutEntity> entityHashMap) {
		saveToStorage(StorageType.CHECKOUT_ENTITY, entityHashMap);
	}




	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}




	@Override
	public void clearCheckoutRecords() {
		try {
			new FileOutputStream(StorageType.CHECKOUT_RECORD.toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clearCheckoutEntities() {
		try {
			new FileOutputStream(StorageType.CHECKOUT_ENTITY.toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearBooks() {
		try {
			new FileOutputStream(StorageType.BOOKS.toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	/////load methods - these place test data into the storage area
	///// - used just once at startup
	@Override
	public void saveNewCheckoutRecord(CheckoutRecord record) {
		HashMap<String, CheckoutRecord> hashMap = readCheckoutRecordMap();
		String isbn = record.getCheckoutId();
		if(hashMap!=null){
			hashMap.put(isbn, record);
			saveToStorage(StorageType.CHECKOUT_RECORD, hashMap);
		}
	}

	@Override
	public void saveNewCheckoutEntity(CheckoutEntity entity) {
		HashMap<String, CheckoutEntity> hashMap = readCheckoutEntityMap();
		String isbn = entity.getEntryId();
		if(hashMap!=null) {
			hashMap.put(isbn, entity);
			saveToStorage(StorageType.CHECKOUT_ENTITY, hashMap);
		}
	}

	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> hashMap = readBooksMap();
		String isbn = book.getIsbn();
		hashMap.put(isbn, book);
		saveToStorage(StorageType.BOOKS, hashMap);
	}
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUT_RECORD);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutEntity> readCheckoutEntityMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, CheckoutEntity>) readFromStorage(StorageType.CHECKOUT_ENTITY);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
	}

	final static class Pair<S,T> implements Serializable{

		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}

}
