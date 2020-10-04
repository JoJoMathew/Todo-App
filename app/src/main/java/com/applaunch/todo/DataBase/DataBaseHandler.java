package com.applaunch.todo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mathew Thomas on 03/10/2020.
 * AppLaunch Limited
 * mathew@applaunch.co.uk
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "records_db";
    private static final String TABLE_Records = "record_details";
    private static final String KEY_ID = "id";
    private static final String TITLE = "title";
    private static final String Desc = "description";
    private static final String _date = "date";
    private static final String _done = "done";


    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Records + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + Desc + " TEXT,"
                + _date + " TEXT,"
                + _done + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Records);
        // Create tables again
        onCreate(db);
    }

    /// ********* CRUD (Create, Read, Update, Delete) Methods ********** ///


    // Add New Record Details
    public void insertUserDetails(String title, String description, String date, String done) {

        // Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(TITLE, title);
        cValues.put(Desc, description);
        cValues.put(_date, date);
        cValues.put(_done, done);

        // Insert new row, returning the primary key value of the new row
        //long newRowId = db.insert(TABLE_Records, null, cValues);
        db.insert(TABLE_Records, null, cValues);
        db.close();
    }

    // Get All Details
    public ArrayList<HashMap<String, String>> GetRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
        String query = "SELECT id, title, description, date, done FROM " + TABLE_Records;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> record = new HashMap<>();
            record.put("title", cursor.getString(cursor.getColumnIndex(TITLE)));
            record.put("desc1", cursor.getString(cursor.getColumnIndex(Desc)));
            record.put("date", cursor.getString(cursor.getColumnIndex(_date)));
            record.put("done", cursor.getString(cursor.getColumnIndex(_done)));
            record.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            dataList.add(record);
        }

        cursor.close();
        return dataList;
    }

    // Get Record Details Based On ID
    public ArrayList<HashMap<String, String>> GetRecordByRecordId(int record_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> record_list = new ArrayList<>();
        // String query = "SELECT qr_code, desc_1, desc_2 desc_3 parts price date FROM "+ TABLE_Records;
        Cursor cursor = db.query(TABLE_Records, new String[]{TITLE, Desc, _date, _done}, KEY_ID + "=?", new String[]{String.valueOf(record_id)}, null, null, null, null);

        if (cursor.moveToNext()) {
            HashMap<String, String> rec = new HashMap<>();
            rec.put("title", cursor.getString(cursor.getColumnIndex(TITLE)));
            rec.put("desc1", cursor.getString(cursor.getColumnIndex(Desc)));
            rec.put("date", cursor.getString(cursor.getColumnIndex(_date)));
            rec.put("done", cursor.getString(cursor.getColumnIndex(_done)));
            rec.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            record_list.add(rec);
        }

        cursor.close();
        return record_list;
    }

    // Delete Record Details
    public void DeleteRecord(int record_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Records, KEY_ID + " = ?", new String[]{String.valueOf(record_id)});
        db.close();
    }

    // Update Record Details
    public int UpdateRecordDetails(String qr_code, String desc1, String date, int id, String done) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(TITLE, qr_code);
        cVals.put(Desc, desc1);
        cVals.put(_date, date);
        cVals.put(_done, done);

        return db.update(TABLE_Records, cVals, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

}
