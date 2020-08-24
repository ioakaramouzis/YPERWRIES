package com.example.karamouzakos_yperwries;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    public static final String TAG = "ListDataActivity";

    DatabaseHelpers mDatabaseHelper;

    private ListView mListView;

    EditText mySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
      //  mySearch = (EditText) findViewById(R.id.searchText);
        mListView = (ListView) findViewById(R.id.list_view_last);
        mDatabaseHelper = new DatabaseHelpers(this);
        populatedListView();


    }


    public void populatedListView() {

        Log.d(TAG, " populated List View : Displaying Data in List View .");
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listdata = new ArrayList<>();
        while (data.moveToNext()) {
            listdata.add(data.getString(1));
        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listdata);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick : U clicked on  " + name);

                Cursor data = mDatabaseHelper.getItemId(name);
                int itemId = -1;
                while (data.moveToNext()) {
                    itemId = data.getInt(0);
                }
                if (itemId > -1) {
                    Log.d(TAG, "OnItemClick:  The ID is: " + itemId);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id", itemId);
                    editScreenIntent.putExtra("name", name);
                    startActivity(editScreenIntent);
                } else {
                    toastMessage("No ID Exists");
                }


            }
        });

    }

    public void toastMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




    }















