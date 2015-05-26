package es.apps.carlos.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import android.database.SQLException;

public class SQLLibrary  {

	public static final String SEARCH_BY_TITTLE = SQLLibraryHelper.DB_TITTLE;
	public static final String SEARCH_BY_AUTHOR = SQLLibraryHelper.DB_AUTHOR;
	public static final String SEARCH_BY_GENDER = SQLLibraryHelper.DB_GENDER;
	public static final String SEARCH_BY_EDITORIAL = SQLLibraryHelper.DB_EDITORIAL;
	public static final String SEARCH_BY_DATE = SQLLibraryHelper.DB_DATE;
	public static final String SEARCH_BY_NONE = "NONE";


	private SQLiteDatabase db;
	private SQLLibraryHelper dbHelper;  

	
	public SQLLibrary(Context context, String name){
		dbHelper = new SQLLibraryHelper(context, name);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();  
	}
	
	public void close() {
		dbHelper.close();
	}


	public Book insertBook(String tittle, String author, String gender, String description, String editorial, Date date) {  
		ContentValues values = new ContentValues();  
		values.put(SQLLibraryHelper.DB_TITTLE, tittle);  
		values.put(SQLLibraryHelper.DB_AUTHOR, author);  
		values.put(SQLLibraryHelper.DB_GENDER, gender);  
		values.put(SQLLibraryHelper.DB_DESCRIPTION, description);  
		values.put(SQLLibraryHelper.DB_EDITORIAL, editorial);  
		values.put(SQLLibraryHelper.DB_DATE, date.toString());
		
		long insertId = db.insert(SQLLibraryHelper.TABLE_BOOKS, null, values);
		String columns[] = {"*"};


		Cursor cursor = db.query(SQLLibraryHelper.TABLE_BOOKS, columns, SQLLibraryHelper.DB_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		
		return cursorToBook(cursor);
	} 
	

	private void deleteBook(long id){
		// TODO: ver que devuelve para status
		db.delete(SQLLibraryHelper.TABLE_BOOKS, SQLLibraryHelper.DB_ID + " = "+ id, null);
	}
	
	public void deleteBook(Book book){
		// TODO: ver que devuelve para status
        deleteBook(book.get_tittle());
	}

    public void deleteBook(String tittle){
        db.delete(SQLLibraryHelper.TABLE_BOOKS,
                SQLLibraryHelper.DB_TITTLE + " = "+ tittle, null);
    }
	
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
        String columns[] = {"*"};

		Cursor cursor = db.query(SQLLibraryHelper.TABLE_BOOKS, columns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			books.add(cursorToBook(cursor));
			cursor.moveToNext();
		}
		
		return books;
	}
	
	// TODO: check whatever happens with date
	public ArrayList<Book> searchBooks(String select, String pattern){
		ArrayList<Book> books = new ArrayList<Book>();
		
		// TODO:  maybe it's necesary to include * after pattern */
		Cursor cursor = db.query(SQLLibraryHelper.TABLE_BOOKS, null, select + " = " + "\"" + pattern + "\"", null, null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			books.add(cursorToBook(cursor));
			cursor.moveToNext();
		}
		
		return books;
	}
	
	public ArrayList<Book> searchBooks(String select[], String pattern[]){
		ArrayList<Book> books = new ArrayList<Book>();
		
		// TODO:  maybe it's necesary to include * after pattern */
		String complex_pattern;
		
		complex_pattern = select[0] + " = " + pattern[0];
		for (int i = 1; i < select.length; i++){
			complex_pattern += " and " ;
			complex_pattern += select[i] + " = " + pattern[i];
		}
        String columns[] = {"*"};
		Cursor cursor = db.query(SQLLibraryHelper.TABLE_BOOKS, columns, complex_pattern, null, null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			books.add(cursorToBook(cursor));
			cursor.moveToNext();
		}
		
		return books;
	}
	
	private Book cursorToBook(Cursor cursor){
		Book book = new Book(cursor.getString(1),
							 cursor.getString(2),
							 cursor.getString(3),
							 cursor.getString(4),
							 cursor.getString(5),
							 new Date(/*cursor.getString(6)*/));
		return book;
	}


}
	