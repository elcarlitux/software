package es.apps.carlos.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


public class AddActivity extends Activity {

    public static final String TAG = "ADD_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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

    public void validate(View view){
        String tittle = ((EditText) findViewById(R.id.txtTittle)).getText().toString();
        String author = ((EditText) findViewById(R.id.txtAuthor)).getText().toString();
        String gender = ((EditText) findViewById(R.id.txtGender)).getText().toString();
        String description = ((EditText) findViewById(R.id.txtDescription)).getText().toString();
        String editorial = ((EditText) findViewById(R.id.txtPublisher)).getText().toString();


        if (tittle.length() == 0){
            Toast.makeText(AddActivity.this, "Tittle is needed", Toast.LENGTH_LONG).show();
        }
        else if (author.length() == 0){
            Toast.makeText(AddActivity.this, "Author is needed", Toast.LENGTH_LONG).show();
        }
        else if (gender.length() == 0){
            Toast.makeText(AddActivity.this, "Gender is needed", Toast.LENGTH_LONG).show();
        }
        else{
            // Add book
            Intent returnIntent = new Intent();

            MainLibrary.library.insertBook(tittle, author, gender, description, editorial,new Date());

            Log.d(TAG, "Inserting book: Tittle: " + tittle + ", author: " + author + ", gender: " + gender + ", description: " + description + ", editorial: " + editorial);

            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    public void cancel(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
