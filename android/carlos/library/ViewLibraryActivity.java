package es.apps.carlos.library;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ViewLibraryActivity extends ListActivity implements OnItemClickListener {

    private SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashMap<String,String> item;

        books = MainLibrary.library.getAllBooks();

        for(int i=0;i<books.size();i++){

            item = new HashMap<String,String>();

            item.put( "line1", books.get(i).get_tittle());

            item.put( "line2", books.get(i).get_gender());

            list.add( item );

        }

        adapter = new SimpleAdapter(this, list, android.R.layout.two_line_list_item , new String[] { "line1","line2" },
                new int[] {android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_library, menu);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, ViewBookActivity.class);
        String tittle = ((HashMap<String,String>)((SimpleAdapter) getListAdapter()).getItem(position)).get("line1");
        intent.putExtra(MainLibrary.EXTRA_BOOK, tittle);
        startActivityForResult(intent, MainLibrary.INTENT_VIEW_BOOK);
    }
}
