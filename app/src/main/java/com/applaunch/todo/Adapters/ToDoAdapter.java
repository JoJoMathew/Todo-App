package com.applaunch.todo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applaunch.todo.Models.TodoDataModel;
import com.applaunch.todo.R;

import java.util.ArrayList;

/**
 * Created by Mathew Thomas on 03/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private ArrayList<TodoDataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDescription, textViewDate;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.title_text_view);
            this.textViewDescription = itemView.findViewById(R.id.desc_textView);
            this.checkBox = itemView.findViewById(R.id.row_checkbox);
            this.textViewDate = itemView.findViewById(R.id.date_textView);
        }
    }

    public ToDoAdapter(ArrayList<TodoDataModel> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_layout, parent, false);
        //view.setOnClickListener(MainActivity.myOnClickListener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDesc = holder.textViewDescription;
        TextView textViewDate = holder.textViewDate;
        CheckBox checkBox = holder.checkBox;

        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewDesc.setText(dataSet.get(listPosition).getDescription());
        textViewDate.setText(dataSet.get(listPosition).getDate());

        if (dataSet.get(listPosition).getIsDone().equals("1")) {
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}