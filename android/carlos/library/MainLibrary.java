package es.apps.carlos.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;


public class MainLibrary extends Activity {


    public static final int INTENT_ADD_BOOK = 1;
    public static final int INTENT_VIEW_LIBRARY = 2;
    public static final int INTENT_VIEW_BOOK = 3;
    public static final int INTENT_SEARCH = 4;


    public static final String EXTRA_TITTLE = "TITTLE";
    public static final String EXTRA_AUTHOR = "AUTHOR";
    public static final String EXTRA_GENDER = "GENDER";
    public static final String EXTRA_EDITORIAL = "EDITORIAL";
    public static final String EXTRA_DESCRIPTION = "DESCRIPTION";
    public static final String EXTRA_DATE = "DATE";
    public static final String EXTRA_BOOKS = "BOOKS";
    public static final String EXTRA_BOOK = "BOOK";

    public static SQLLibrary library;
    public static final String LIBRARY_NAME = "Books";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        library = new SQLLibrary(getApplicationContext(), LIBRARY_NAME);
        library.open();
        setContentView(R.layout.main_library);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_library, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addBook(View view){
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent, INTENT_ADD_BOOK);
    }

    public void view(View view){
        Intent intent = new Intent(this, ViewLibraryActivity.class);
        startActivityForResult(intent, INTENT_VIEW_LIBRARY);
    }

    public void search(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, INTENT_VIEW_LIBRARY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }
}
