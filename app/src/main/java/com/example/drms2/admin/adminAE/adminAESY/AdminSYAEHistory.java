package com.example.drms2.admin.adminAE.adminAESY;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.DatabaseHelper;
import com.example.drms2.R;

import java.util.ArrayList;

public class AdminSYAEHistory extends AppCompatActivity {
    TextView tvroll, tvStudInfo, tvGetStudinfo, tvSession, tvSrNo, tvSubjectName, tvSubjectTeacher, tvAdjustedConducted, tvSyllabusContents, tvRating;
    TableRow newRow;
    EditText etStudInfo, etGetStudInfo, etSrNo, etSession, etSubjectName, etSubjectTeacher, etAdjustedConducted, etSyllabusContents, etRating;

    EditText etRollNo,etDate;
    String rollNo = "";
    Button btnFetch, btnUpdate, btnSave,btnDalete;
    public TableLayout tableReport;
    public DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_report); // Make sure to set your layout

        // Initialize views
        etRollNo = findViewById(R.id.etRollNo);
        btnFetch = findViewById(R.id.btnFetch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSave = findViewById(R.id.btnSave);
        btnDalete=findViewById(R.id.btnDelete);
        tableReport = findViewById(R.id.tableReport);
        etDate=findViewById(R.id.etDate);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set onClick listener for the fetch button
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchReport();
            }
        });
        btnDalete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteReport();
            }
        });
        // Set onClick listener for the update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateReport();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUpdatedReport();
            }
        });
    }

    @SuppressLint({"SetTextI18n", "NewApi"})
    private void fetchReport() {
        rollNo = etRollNo.getText().toString().trim();
        if (rollNo.isEmpty()) {
            Toast.makeText(this, "Please enter a roll number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Clear previous rows
        tableReport.removeViews(1, tableReport.getChildCount() - 1);
        Cursor cursor = dbHelper.getReportsByRollNumberRaw(rollNo);// Remove all rows except the header
        if(rollNo.startsWith("AE2"))
        {


            if (cursor != null && cursor.moveToFirst()) {
                int rowCount = 1; // Initialize a counter for SrNo
                do {
                    // Get column indices
                    int studInfoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_STUD_INFO);
                    int getStudInfoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_GET_STUD_INFO);
                    int sessionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SESSION);
                    int subjectNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUBJECT_NAME);
                    int subjectTeacherIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUBJECT_TEACHER);
                    int adjustedConductedIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ADJUSTED_CONDUCTED);
                    int syllabusContentsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SYLLABUS_CONTENTS);
                    int ratingIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING);

                    // Check if indices are valid
                    if (studInfoIndex == -1 || getStudInfoIndex == -1 || sessionIndex == -1 ||
                            subjectNameIndex == -1 || subjectTeacherIndex == -1 || adjustedConductedIndex == -1 ||
                            syllabusContentsIndex == -1 || ratingIndex == -1) {
                        Toast.makeText(this, "Error: One or more columns do not exist in the database.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Fetch data using valid indices
                    String studinfo = cursor.getString(studInfoIndex);
                    String getstudinfo = cursor.getString(getStudInfoIndex);
                    String srno = valueOf(rowCount); // Set SrNo using the counter
                    String session = cursor.getString(sessionIndex);
                    String subjectName = cursor.getString(subjectNameIndex);
                    String subjectTeacher = cursor.getString(subjectTeacherIndex);
                    String adjustedConducted = cursor.getString(adjustedConductedIndex);
                    String syllabusContents = cursor.getString(syllabusContentsIndex);
                    float rating = cursor.getFloat(ratingIndex);

                    // Create a new ro
                    newRow = new TableRow(this);
                    newRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    // Create TextViews for each piece of data
                    tvStudInfo = createCell(studinfo);
                    tvGetStudinfo = createCell(getstudinfo);
                    tvSrNo = createCell(srno); // Use the counter for SrNo
                    tvSession = createCell(session);
                    tvSubjectName = createCell(subjectName);
                    tvSubjectTeacher = createCell(subjectTeacher);
                    tvAdjustedConducted = createCell(adjustedConducted);
                    tvSyllabusContents = createCell(syllabusContents);
                    tvRating = createCell(valueOf(rating));

                    // Add TextViews to the new row
                    newRow.addView(tvStudInfo);
                    newRow.addView(tvGetStudinfo);
                    newRow.addView(tvSrNo);
                    newRow.addView(tvSession);
                    newRow.addView(tvSubjectName);
                    newRow.addView(tvSubjectTeacher);
                    newRow.addView(tvAdjustedConducted);
                    newRow.addView(tvSyllabusContents);
                    newRow.addView(tvRating);

                    // Add the new row to the table
                    tableReport.addView(newRow);
                    newRow.setVisibility(View.VISIBLE);

                    rowCount++; // Increment the counter for the next row
                } while (cursor.moveToNext());

                // Show the table
                tableReport.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "No report found for this roll number", Toast.LENGTH_SHORT).show();
                tableReport.setVisibility(View.GONE); // Hide the table if no report found
            }
        }
        else {
            Toast.makeText(this, "No report found for this roll number", Toast.LENGTH_SHORT).show();
            tableReport.setVisibility(View.GONE); // Hide the table if no report found
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private TextView createCell(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.edittext_border); // Set the border drawable
        textView.setPadding(8, 8, 8, 8); // Add padding for better appearance
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }


    public void updateReport() {
        String date = etDate.getText().toString().trim();
        rollNo=etRollNo.getText().toString();
        if (date.isEmpty()) {
            Toast.makeText(this, "Please enter a roll date", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = dbHelper.getReportsByDaterRaw(date,rollNo);
        if(rollNo.startsWith("AE2"))
        {
            if (cursor != null && cursor.moveToFirst()) {
                // Clear previous rows in the table
                tableReport.removeViews(1, tableReport.getChildCount() - 1); // Remove all rows except the header

                int rowCount = 1; // Initialize a counter for SrNo
                do {
                    // Assuming you have the same number of fields as in the database
                    int reportIdIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_REPORT_ID);
                    int reportId = cursor.getInt(reportIdIndex);
                    int studInfoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_STUD_INFO);
                    int getStudInfoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_GET_STUD_INFO);
                    int sessionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SESSION);
                    int subjectNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUBJECT_NAME);
                    int subjectTeacherIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUBJECT_TEACHER);
                    int adjustedConductedIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ADJUSTED_CONDUCTED);
                    int syllabusContentsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SYLLABUS_CONTENTS);
                    int ratingIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING);

                    // Check if indices are valid
                    if (studInfoIndex == -1 || getStudInfoIndex == -1 || sessionIndex == -1 ||
                            subjectNameIndex == -1 || subjectTeacherIndex == -1 || adjustedConductedIndex == -1 ||
                            syllabusContentsIndex == -1 || ratingIndex == -1) {
                        Toast.makeText(this, "Error: One or more columns do not exist in the database.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Fetch data using valid indices
                    String studinfo = cursor.getString(studInfoIndex);
                    String getstudinfo = cursor.getString(getStudInfoIndex);
                    String srno = valueOf(rowCount); // Set SrNo using the counter
                    String session = cursor.getString(sessionIndex);
                    String subjectName = cursor.getString(subjectNameIndex);
                    String subjectTeacher = cursor.getString(subjectTeacherIndex);
                    String adjustedConducted = cursor.getString(adjustedConductedIndex);
                    String syllabusContents = cursor.getString(syllabusContentsIndex);
                    float rating = cursor.getFloat(ratingIndex);

                    // Create a new row for editing
                    newRow = new TableRow(this);
                    newRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    // Create TextViews for studInfo, srNo, and session
                    newRow.setTag(reportId);
                    TextView tvStudInfo = new TextView(this);
                    tvStudInfo.setText(studinfo);
                    newRow.addView(tvStudInfo);

                    EditText etGetStudInfo = new EditText(this);
                    etGetStudInfo.setText(getstudinfo);
                    newRow.addView(etGetStudInfo);

                    TextView tvSrNo = new TextView(this);
                    tvSrNo.setText(srno); // Display current row number as SrNo
                    newRow.addView(tvSrNo);

                    TextView tvSession = new TextView(this);
                    tvSession.setText(session);
                    newRow.addView(tvSession);

                    // Create EditTexts for fields that can be updated
                    EditText etSubjectName = new EditText(this);
                    etSubjectName.setText(subjectName);
                    newRow.addView(etSubjectName);

                    EditText etSubjectTeacher = new EditText(this);
                    etSubjectTeacher.setText(subjectTeacher);
                    newRow.addView(etSubjectTeacher);

                    EditText etAdjustedConducted = new EditText(this);
                    etAdjustedConducted.setText(adjustedConducted);
                    newRow.addView(etAdjustedConducted);

                    EditText etSyllabusContents = new EditText(this);
                    etSyllabusContents.setText(syllabusContents);
                    newRow.addView(etSyllabusContents);

                    EditText etRating = new EditText(this);
                    etRating.setText(valueOf(rating));
                    newRow.addView(etRating);

                    // Add the new row to the table for editing
                    tableReport.addView(newRow);
                    newRow.setVisibility(View.VISIBLE);

                    rowCount++; // Increment the counter for the next row
                } while (cursor.moveToNext());

                // Show the table for editing
                tableReport.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "No report found for this roll number", Toast.LENGTH_SHORT).show();
                tableReport.setVisibility(View.GONE);


            }
        } else {
            Toast.makeText(this, "No report found for this roll number", Toast.LENGTH_SHORT).show();
            tableReport.setVisibility(View.GONE); // Hide the table if no report found
        }

        if (cursor != null) {
            cursor.close();
        }
    }
    private void saveUpdatedReport() {
        String date = etDate.getText().toString().trim(); // Get the date from the EditText
        if (date.isEmpty()) {
            Toast.makeText(this, "Please enter a date", Toast.LENGTH_SHORT).show();
            return;
        }

        // Iterate through all rows in the table and collect data column-wise
        for (int i = 1; i < tableReport.getChildCount(); i++) { // Start from 1 to skip header
            TableRow row = (TableRow) tableReport.getChildAt(i);

            // Retrieve the report ID from the row's tag
            int reportId = (int) row.getTag();

            // Collect updated values from EditTexts and TextViews
            EditText etGetStudInfo = (EditText) row.getChildAt(1);
            TextView tvStudInfo = (TextView) row.getChildAt(0);
            TextView tvSession = (TextView) row.getChildAt(3);
            EditText etSubjectName = (EditText) row.getChildAt(4);
            EditText etSubjectTeacher = (EditText) row.getChildAt(5);
            EditText etAdjustedConducted = (EditText) row.getChildAt(6);
            EditText etSyllabusContents = (EditText) row.getChildAt(7);
            EditText etRating = (EditText) row.getChildAt(8);

            // Call the update method from DatabaseHelper
            boolean isUpdated = dbHelper.updateReportById(reportId,
                    etRollNo.getText().toString().trim(),
                    tvStudInfo.getText().toString().trim(),
                    etGetStudInfo.getText().toString().trim(),
                    tvSrNo.getText().toString().trim(),
                    tvSession.getText().toString().trim(),
                    etSubjectName.getText().toString().trim(),
                    etSubjectTeacher.getText().toString().trim(),
                    etAdjustedConducted.getText().toString().trim(),
                    etSyllabusContents.getText().toString().trim(),
                    etRating.getText().toString().trim(),
                    etDate.getText().toString().trim() // Pass the date as well
            );

            if (!isUpdated) {
                Toast.makeText(this, "Failed to update report with ID: " + reportId, Toast.LENGTH_SHORT).show();
            }
        }

        Toast.makeText(this, "Reports updated successfully!", Toast.LENGTH_SHORT).show();
    }
    public void deleteReport() {
        String date = etDate.getText().toString().trim();
        String roll=etRollNo.getText().toString();// Get date from EditText

        if (date.isEmpty()|| roll.isEmpty()) {
            Toast.makeText(this, "Please enter a date and roll number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(roll.startsWith("AE2")&&(! date.isEmpty()))
        {
            // Call the delete method from DatabaseHelper
            int deletedRows = dbHelper.deleteReportsByDate(date,rollNo); // Delete by date

            if (deletedRows > 0) {
                Toast.makeText(this, "Report(s) deleted successfully!", Toast.LENGTH_SHORT).show();
            }} else {
            Toast.makeText(this, "No report found for this date", Toast.LENGTH_SHORT).show();
        }
    }
}