package com.lms.dataaccess;
import com.lms.model.Book;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class DataAccessFacade implements DataAccess {

	public static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	enum StorageType {
		BOOKS, MEMBERS, USERS;
	}

	public static final String OUTPUT_DIR = "C:/pprajapati/Projects/lms/src/com/lms/dataaccess/storage";

	public static final String DATE_PATTERN = "MM/dd/yyyy";


	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}


	@Override
	public void loadBooks(HashMap<String, Book> bookList) {
		saveToStorage(StorageType.BOOKS, bookList);
	}

	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> hashMap = readBooksMap();
		saveToStorage(StorageType.BOOKS, hashMap);
	}

	public void clearBooks() {
		try {
			new FileOutputStream(StorageType.BOOKS.toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
