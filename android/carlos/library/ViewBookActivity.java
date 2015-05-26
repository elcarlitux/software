package es.apps.carlos.library;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewBookActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        String tittle = getIntent().getStringExtra(MainLibrary.EXTRA_BOOK);

        ArrayList<Book> books = MainLibrary.library.searchBooks(SQLLibrary.SEARCH_BY_TITTLE, tittle);

        ((TextView) findViewById(R.id.txtTittle)).setText(tittle);
        ((TextView) findViewById(R.id.txtAuthor)).setText(books.get(0).get_author());
        ((TextView) findViewById(R.id.txtGender)).setText(books.get(0).get_gender());
        ((TextView) findViewById(R.id.txtEditorial)).setText(books.get(0).get_editorial());
        ((TextView) findViewById(R.id.txtDescription)).setText(books.get(0).get_description());
        ((TextView) findViewById(R.id.txtDate)).setText(books.get(0).get_date().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_book, menu);
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
}
