package com.applaunch.todo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.applaunch.todo.Adapters.ToDoAdapter;
import com.applaunch.todo.DataBase.DataBaseHandler;
import com.applaunch.todo.Models.TodoDataModel;
import com.applaunch.todo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Mathew Thomas on 03/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

@SuppressWarnings("rawtypes")
public class MainActivity extends AppCompatActivity {

    private DataBaseHandler DBHandler;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<TodoDataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.todo_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();

        // Init Database Handler
        DBHandler = new DataBaseHandler(this);
        //DBHandler.insertUserDetails("Test 6", "party", "18/10/2020", "0"); // <---- DEBUG, Insert Data

        // Populate RecyclerView With Data
        ArrayList<HashMap<String, String>> dataList =  DBHandler.GetRecords();
        for (int i = 0; i < dataList.size(); i++) {
            data.add(new TodoDataModel(
                    Integer.parseInt(Objects.requireNonNull(dataList.get(i).get("id"))),
                    dataList.get(i).get("title"),
                    dataList.get(i).get("desc1"),
                    dataList.get(i).get("date"),
                    dataList.get(i).get("done")
            ));
        }

        adapter = new ToDoAdapter(data);
        recyclerView.setAdapter(adapter);

        // Floating Action Button
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch AddNew Activity
                Intent myIntent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(myIntent);
            }
        });

    }

    /// Settings Menu Button ///
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_button) {
            // Launch Settings Activity
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}