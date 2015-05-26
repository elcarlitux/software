package es.apps.carlos.library;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteDatabase.CursorFactory;  
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

   
    
public class SQLLibraryHelper extends SQLiteOpenHelper {
        
	public static final String DB_ID 	= "_id"; 
	public static final String DB_TITTLE = "tittle" ;
	public static final String DB_AUTHOR = "author" ;
	public static final String DB_GENDER = "gender" ;
	public static final String DB_DESCRIPTION = "description" ;
	public static final String DB_EDITORIAL = "editorial" ;
	public static final String DB_DATE = "date" ;
	public static final String TABLE_BOOKS = "books";

    
	private static final int VERSION = 1;   	
	private static final String DB_LIBRARY = "library";
	
	//private static final String DB_LIBRARY_CREATE = "create table "
	//	+ DB_LIBRARY + "( _id integer primary key autoincrement, " 
	//	+ "name text not null);";
		
	private static final String TABLE_BOOKS_CREATE =  "create table "
		+ TABLE_BOOKS + "( " + DB_ID + " integer primary key autoincrement, "
		+ DB_TITTLE + "  text not null, "
		+ DB_AUTHOR + " text not null, "
		+ DB_GENDER + " text not null, "
		+ DB_DESCRIPTION + " text, "
		+ DB_EDITORIAL + " text,"
		+ DB_DATE + " date not null);";
		
    
	
	public SQLLibraryHelper(Context context, String name, CursorFactory factory, int version) {
        //super    
        super(context, name, factory, version);    
        // TODO Auto-generated constructor stub    
    }    
    
	public SQLLibraryHelper(Context context,String name){
        this(context, name, null, VERSION);    
    }    
    
	public SQLLibraryHelper(Context context,String name,int version){
        this(context, name,null,version);    
    }    
    
	
    //,SQLiteDatabse?    
    @Override    
    public void onCreate(SQLiteDatabase db) {    
        // TODO Auto-generated method stub    
        System.out.println("create a Database");
		db.execSQL(TABLE_BOOKS_CREATE);
    }    
    
    @Override    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    
		Log.w(SQLLibraryHelper.class.getName(), 
				"Upgrading database from version " + oldVersion + " to  " + newVersion +
				", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		onCreate(db);   
    }    
    
}  