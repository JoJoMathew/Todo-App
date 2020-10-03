package com.applaunch.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.applaunch.todo.DataBase.DataBaseHandler;

/**
 * Created by Mathew Thomas on 03/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

public class MainActivity extends AppCompatActivity {

    private DataBaseHandler DBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Database Handler
        DBHandler = new DataBaseHandler(this);
    }
}