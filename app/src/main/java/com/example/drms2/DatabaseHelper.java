package com.example.drms2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reports.db";
    public static final int DATABASE_VERSION = 2; // Incremented version for the new column

    // Table names
    public static final String TABLE_REPORTS = "reports";
    public static final String TABLE_HOD_LOGIN="hod";
    public static final String COLUMN_HOD_USERNAME="user_hod";
    public static final String COLUMN_ADMIN_USERNAME="user_admin";
    public static final String COLUMN_PRINCIPAL_USERNAME="user_principal";
    public static final String COLUMN_HOD_ID="id_hod";
    public static final String COLUMN_ADMIN_ID="id_admin";
    public static final String COLUMN_PRINCIPAL_ID="id_principal";
    public static final String COLUMN_HOD_PASSWORD="password_hod";
    public static final String COLUMN_ADMIN_PASSWORD="password_admin";
    public static final String COLUMN_PRINCIPAL_PASSWORD="password_principal";
    public static final String TABLE_PRINCIPAL_LOGIN="principal";
    public static final String TABLE_ADMIN_LOGIN="admin";
    public static final String TABLE_KPC_LOGIN="kpc";
    public static final String COLUMN_KPC_ID="kpc_id";
    public static final String COLUMN_KPC_USERNAME="kpc_user";
    public static final String COLUMN_KPC_PASSWORD="kpc_password";





    // Columns for reports table
    public static final String COLUMN_STUD_INFO = "stud_info";
    public static final String COLUMN_GET_STUD_INFO = "get_stud_info";
    public static final String COLUMN_ROLL_NO = "roll_no";
    public static final String COLUMN_REPORT_ID = "id";
    public static final String COLUMN_SR_NO = "sr_no";
    public static final String COLUMN_SESSION = "session";
    public static final String COLUMN_SUBJECT_NAME = "subject_name";
    public static final String COLUMN_SUBJECT_TEACHER = "subject_teacher";
    public static final String COLUMN_ADJUSTED_CONDUCTED = "adjusted_conducted";
    public static final String COLUMN_SYLLABUS_CONTENTS = "syllabus_contents";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_DATE = "date"; // New column for date
    public static final String COLUMN_STATUS = "status"; // New column for status

    // Create reports table SQL query
     // Add the status column

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_REPORTS =
                "CREATE TABLE " + TABLE_REPORTS + "("
                        + COLUMN_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ROLL_NO + " TEXT,"
                        + COLUMN_STUD_INFO + " TEXT,"
                        + COLUMN_GET_STUD_INFO + " TEXT,"
                        + COLUMN_SR_NO + " TEXT,"
                        + COLUMN_SESSION + " TEXT,"
                        + COLUMN_SUBJECT_NAME + " TEXT,"
                        + COLUMN_SUBJECT_TEACHER + " TEXT,"
                        + COLUMN_ADJUSTED_CONDUCTED + " TEXT,"
                        + COLUMN_SYLLABUS_CONTENTS + " TEXT,"
                        + COLUMN_RATING + " REAL,"
                        + COLUMN_DATE + " TEXT,"
                        + COLUMN_STATUS + " TEXT" + ")";
        String CREATE_HOD_SIGNUP =
                "CREATE TABLE " + TABLE_HOD_LOGIN + "("
                        + COLUMN_HOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_HOD_USERNAME + " TEXT,"
                        + COLUMN_HOD_PASSWORD + " TEXT)";
        String CREATE_ADMIN_SIGNUP =
                "CREATE TABLE " + TABLE_ADMIN_LOGIN + "("
                        + COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ADMIN_USERNAME + " TEXT,"
                        + COLUMN_ADMIN_PASSWORD + " TEXT)";
        String CREATE_PRINCIPAL_SIGNUP =
                "CREATE TABLE " + TABLE_PRINCIPAL_LOGIN + "("
                        + COLUMN_PRINCIPAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PRINCIPAL_USERNAME + " TEXT,"
                        + COLUMN_PRINCIPAL_PASSWORD + " TEXT)";
        String CREATE_KPC_SIGNUP =
                "CREATE TABLE " + TABLE_KPC_LOGIN + "("
                        + COLUMN_KPC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_KPC_USERNAME + " TEXT,"
                        + COLUMN_KPC_PASSWORD + " TEXT)";
        db.execSQL(CREATE_ADMIN_SIGNUP);
        db.execSQL(CREATE_PRINCIPAL_SIGNUP);
        db.execSQL(CREATE_HOD_SIGNUP);
        db.execSQL(CREATE_TABLE_REPORTS);
        db.execSQL(CREATE_KPC_SIGNUP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) { // Check if the old version is less than 2
            db.execSQL("ALTER TABLE " + TABLE_REPORTS + " ADD COLUMN " + COLUMN_DATE + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_REPORTS + " ADD COLUMN " + COLUMN_STATUS + " TEXT");
        }
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOD_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRINCIPAL_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPORTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_KPC_LOGIN);
    }

    public boolean insertHODSignup(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_HOD_USERNAME,username);
        values.put(COLUMN_HOD_PASSWORD,password);
        long iRow=db.insert(TABLE_HOD_LOGIN,null,values);
        if(iRow>-1)
        {
            return  true;
        }else {
            return false;
        }

    }
    public boolean insertKPCSignup(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_KPC_USERNAME,username);
        values.put(COLUMN_KPC_PASSWORD,password);
        long iRow=db.insert(TABLE_KPC_LOGIN,null,values);
        if(iRow>-1)
        {
            return  true;
        }else {
            return false;
        }

    }
    public boolean insertAdminSignup(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_ADMIN_USERNAME,username);
        values.put(COLUMN_ADMIN_PASSWORD,password);
        long iRow=db.insert(TABLE_ADMIN_LOGIN,null,values);
        if(iRow>-1)
        {
            return  true;
        }else {
            return false;
        }

    }
    public boolean insertPrincipalSignup(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_PRINCIPAL_USERNAME,username);
        values.put(COLUMN_PRINCIPAL_PASSWORD,password);
        long iRow=db.insert(TABLE_PRINCIPAL_LOGIN,null,values);
        if(iRow>-1)
        {
            return  true;
        }else {
            return false;
        }

    }
    public boolean validateAdmin(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_ADMIN_LOGIN, new String[]{COLUMN_ADMIN_USERNAME,COLUMN_ADMIN_PASSWORD},COLUMN_ADMIN_USERNAME+" =? AND "+COLUMN_ADMIN_PASSWORD+ "=?",new String[]{username,password},null,null,null);
        if(cursor!=null && cursor.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }
    public boolean validateKPC(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_KPC_LOGIN, new String[]{COLUMN_KPC_USERNAME,COLUMN_KPC_PASSWORD},COLUMN_KPC_USERNAME+" =? AND "+COLUMN_KPC_PASSWORD+ "=?",new String[]{username,password},null,null,null);
        if(cursor!=null && cursor.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }
    public boolean validateHOD(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_HOD_LOGIN, new String[]{COLUMN_HOD_USERNAME,COLUMN_HOD_PASSWORD},COLUMN_HOD_USERNAME+" =? AND "+COLUMN_HOD_PASSWORD+ "=?",new String[]{username,password},null,null,null);
        if(cursor!=null && cursor.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }
    public boolean validatePrincipal(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_PRINCIPAL_LOGIN, new String[]{COLUMN_PRINCIPAL_USERNAME,COLUMN_PRINCIPAL_PASSWORD},COLUMN_PRINCIPAL_USERNAME+" =? AND "+COLUMN_PRINCIPAL_PASSWORD+ "=?",new String[]{username,password},null,null,null);
        if(cursor!=null && cursor.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }

    // Method to insert report data
    public void insertReport(String rollNo, String studinfo, String getstudinfo, String srNo, String session, String subjectName, String subjectTeacher, String adjustedConducted, String syllabusContents, String rating, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLL_NO, rollNo);
        values.put(COLUMN_STUD_INFO, studinfo);
        values.put(COLUMN_GET_STUD_INFO, getstudinfo);
        values.put(COLUMN_SR_NO, srNo);
        values.put(COLUMN_SESSION, session);
        values.put(COLUMN_SUBJECT_NAME, subjectName);
        values.put(COLUMN_SUBJECT_TEACHER, subjectTeacher);
        values.put(COLUMN_ADJUSTED_CONDUCTED, adjustedConducted);
        values.put(COLUMN_SYLLABUS_CONTENTS, syllabusContents);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_STATUS, "pending"); // Set status to pending
        db.insert(TABLE_REPORTS, null, values);
        db.close();
    }

    // Method to fetch pending reports


    // Method to update report status after review
    public void updateReportStatus(int reportId, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS, newStatus);
        db.update(TABLE_REPORTS, values, COLUMN_REPORT_ID + " = ?", new String[]{String.valueOf(reportId)});
        db.close();
    }

    // Method to fetch reports by roll number
    public Cursor getReportsByRollNumberRaw(String rollNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_REPORTS + " WHERE " + COLUMN_ROLL_NO + " = ?";
        return db.rawQuery(sql, new String[]{rollNumber});
    }
    public Cursor getReportsByDaterRaw(String date, String rollNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Correctly format the SQL query with placeholders
        String sql = "SELECT * FROM " + TABLE_REPORTS + " WHERE " + COLUMN_DATE + " = ? AND " + COLUMN_ROLL_NO + " = ?";
        // Pass the parameters as an array
        return db.rawQuery(sql, new String[]{date, rollNo});
    }
    public int deleteReportsByDate(String date,String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_REPORTS, COLUMN_DATE + "= ? AND " + COLUMN_ROLL_NO + " = ?", new String[]{date,rollno});

    }

    public boolean updateReportById(int reportId, String rollNo, String studinfo, String getstudinfo, String srNo, String session, String subjectName, String subjectTeacher, String adjustedConducted, String syllabusContents, String ratings, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLL_NO, rollNo);
        values.put(COLUMN_STUD_INFO, studinfo);
        values.put(COLUMN_GET_STUD_INFO, getstudinfo);
        values.put(COLUMN_SR_NO, srNo);
        values.put(COLUMN_SESSION, session);
        values.put(COLUMN_SUBJECT_NAME, subjectName);
        values.put(COLUMN_SUBJECT_TEACHER, subjectTeacher);
        values.put(COLUMN_ADJUSTED_CONDUCTED, adjustedConducted);
        values.put(COLUMN_SYLLABUS_CONTENTS, syllabusContents);
        values.put(COLUMN_RATING, ratings);
        values.put(COLUMN_DATE, date); // Add the date to the values

        // Update the report where the report ID matches
        int rowsAffected = db.update(TABLE_REPORTS, values, COLUMN_REPORT_ID + "=?", new String[]{String.valueOf(reportId)});
        db.close();

        return rowsAffected > 0; // Return true if at least one row was updated
    }
}