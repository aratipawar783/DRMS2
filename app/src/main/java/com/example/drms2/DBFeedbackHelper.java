package com.example.drms2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBFeedbackHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "feedbackreports.db";
    public static final int DATABASE_VERSION = 3; // Incremented version for the new column

    // Table names
    public static final String TABLE_REPORTS = "feedbackreports";

    // Columns for reports table
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FEEDBACK= "feedback";

    public static final String COLUMN_REPORT_ID = "id";

    public static final String COLUMN_DATE = "date"; // New column for date


    // Create reports table SQL query
    public static final String CREATE_TABLE_REPORTS =
            "CREATE TABLE " + TABLE_REPORTS + "("
                    + COLUMN_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SUBJECT + " TEXT,"
                    + COLUMN_FEEDBACK + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_NAME + " TEXT"
                     + ")"; // Add the status column

    public DBFeedbackHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REPORTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) { // Check if the old version is less than 2
            db.execSQL("ALTER TABLE " + TABLE_REPORTS + " ADD COLUMN " + COLUMN_DATE + " TEXT");

        }
    }

    // Method to insert report data
    public void insertReport(String subject, String feedback, String date,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT,subject);
        values.put(COLUMN_FEEDBACK,feedback);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_NAME,name);
        // Set status to pending
        db.insert(TABLE_REPORTS, null, values);
        db.close();
    }

    // Method to fetch pending reports


    // Method to update report status after review


    // Method to fetch reports by roll number

    public Cursor getReportsByDaterRaw(String date, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_REPORTS + " WHERE " + COLUMN_DATE + " = ? AND " + COLUMN_NAME + " = ?";
        return db.rawQuery(sql, new String[]{date, name});
    }


}