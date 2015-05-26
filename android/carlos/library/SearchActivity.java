package es.apps.carlos.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchActivity extends Activity implements OnItemSelectedListener, TextWatcher {

    private static final int SEARCH_BY_NONE = -1;

    private static final String[][] SearchBy = {
            {"Search by", SQLLibrary.SEARCH_BY_NONE},
            {"Tittle", SQLLibrary.SEARCH_BY_TITTLE},
            {"Author", SQLLibrary.SEARCH_BY_AUTHOR},
            {"Gender", SQLLibrary.SEARCH_BY_GENDER},
            {"Editorial", SQLLibrary.SEARCH_BY_EDITORIAL}};

    private static ArrayList<Integer[]> searchingBy ;
    private static ArrayList<Integer[]> pattern ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner1 = ((Spinner)findViewById(R.id.spinner1));

        List<String> list = new ArrayList<String>();

        list.add(SearchBy[0][0]);
        list.add(SearchBy[1][0]);
        list.add(SearchBy[2][0]);
        list.add(SearchBy[3][0]);
        list.add(SearchBy[4][0]);


        searchingBy = new ArrayList<Integer[]>();
        searchingBy.add(new Integer[] {R.id.spinner1, SEARCH_BY_NONE});

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(this);

        pattern = new ArrayList<Integer[]>();
        EditText text = (EditText)findViewById(R.id.txt);
        text.addTextChangedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        for (int i = 0 ; i < searchingBy.size(); i++){
            // find the searching by needed
            if (searchingBy.get(i)[0] == adapterView.getId()) {
                searchingBy.get(i)[1] = position;
                // Add another search by if there's no one "empty"
                if (searchingBy.size() <= (i+1) && (position != 0)){
                    // Add
                    LinearLayout LL = new LinearLayout(getApplicationContext());
                    LL.setOrientation(LinearLayout.HORIZONTAL);

                    LayoutParams LLParams = findViewById(R.id.intLayout).getLayoutParams();

                    LL.setLayoutParams(LLParams);

                    EditText editTextView = new EditText(getApplicationContext());
                    LLParams = findViewById(R.id.txt).getLayoutParams();
                    editTextView.setLayoutParams(LLParams);
                    editTextView.setEms(((EditText)findViewById(R.id.txt)).getWidth());
                    editTextView.setTextColor(Color.BLACK);

                    Spinner spinner = new Spinner(getApplicationContext());
                    LLParams = findViewById(R.id.spinner1).getLayoutParams();
                    spinner.setLayoutParams(LLParams);

                    List<String> list = new ArrayList<String>();

                    list.add(SearchBy[0][0]);
                    list.add(SearchBy[1][0]);
                    list.add(SearchBy[2][0]);
                    list.add(SearchBy[3][0]);
                    list.add(SearchBy[4][0]);


                    searchingBy.add(new Integer[] {spinner.getId(), SEARCH_BY_NONE});

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                            (this, android.R.layout.simple_spinner_item,list);

                    dataAdapter.setDropDownViewResource
                            (android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(dataAdapter);


                    spinner.setOnItemSelectedListener(this);

                    LL.addView(spinner);
                    LL.addView(editTextView);

                    LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
                    layout.addView(LL);

                    return;

                }
            }
        }


    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void search(View view){
        String select[];
        String pattern[];

        select = new String[searchingBy.size()];
        pattern = new String[searchingBy.size()];

        for (int i = 0; i < searchingBy.size(); i++){
            int ind = searchingBy.get(i)[1];
            if (ind != SEARCH_BY_NONE) {
                select[i] = SearchBy[searchingBy.get(i)[1]][1];
                //pattern[i] =
            }
            else{
                select[i] = SQLLibrary.SEARCH_BY_NONE;
            }
        }

    }

    public void clear(View view){
        setContentView(R.layout.activity_search);

        Spinner spinner1 = ((Spinner)findViewById(R.id.spinner1));

        List<String> list = new ArrayList<String>();

        list.add(SearchBy[0][0]);
        list.add(SearchBy[1][0]);
        list.add(SearchBy[2][0]);
        list.add(SearchBy[3][0]);
        list.add(SearchBy[4][0]);


        searchingBy = new ArrayList<Integer[]>();
        searchingBy.add(new Integer[] {R.id.spinner1, SEARCH_BY_NONE});

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
