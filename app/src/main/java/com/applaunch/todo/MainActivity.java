package com.applaunch.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.applaunch.todo.Adapters.ToDoAdapter;
import com.applaunch.todo.DataBase.DataBaseHandler;
import com.applaunch.todo.Models.TodoDataModel;

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
        //DBHandler.insertUserDetails("Test 2", "some other description", "14/10/2020", "0"); // <---- DEBUG, Insert Data

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

    }
}