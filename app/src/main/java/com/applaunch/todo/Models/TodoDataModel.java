package com.applaunch.todo.Models;

/**
 * Created by Mathew Thomas on 04/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

public class TodoDataModel {

    int id;
    String title;
    String description;
    String date;
    String done;

    public TodoDataModel(int id, String title, String desc, String date, String done) {
        this.title = title;
        this.description = desc;
        this.id = id;
        this.date = date;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getIsDone() { return done; }

}
