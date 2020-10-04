package com.applaunch.todo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.applaunch.todo.R;

import java.util.Objects;

/**
 * Created by Mathew Thomas on 04/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

public class AddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        // Title
        setTitle("Todo");

        // Back Button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    // Add New Entry
    public void SaveNewTodo(View v) {
        // Todo ... save new todo entry
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}