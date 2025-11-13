package com.example.drms2.principal.EE.EESY;

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

import com.example.drms2.DBFeedbackHelper;
import com.example.drms2.R;

public class ViewFeedbackEESy extends AppCompatActivity {
    TextView tvroll, tvStudInfo, tvGetStudinfo, tvSession, tvSrNo, tvSubjectName, tvSubjectTeacher, tvAdjustedConducted, tvSyllabusContents, tvRating;
    TableRow newRow;
    EditText etStudInfo, etGetStudInfo, etSrNo, etSession, etSubjectName, etSubjectTeacher, etAdjustedConducted, etSyllabusContents, etRating;

    EditText etRollNo,etDate,etName;
    String name1="",date1="";
    String rollNo = "";
    Button btnFetch, btnUpdate, btnSave,btnDalete;
    public TableLayout tableReport;
    public DBFeedbackHelper dbHelper;
    String name="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback_eesy); // Make sure to set your layout

        // Initialize views

        btnFetch = findViewById(R.id.btnFetch);
        tableReport = findViewById(R.id.tableReport);
        etDate=findViewById(R.id.etDate);
        etName=findViewById(R.id.etName);

        // Initialize database helper
        dbHelper = new DBFeedbackHelper(this);

        // Set onClick listener for the fetch button
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=etName.getText().toString();
                if(name.equalsIgnoreCase("Electrical Engineering Second Year")) {
                    fetchReport();
                }else {
                    Toast.makeText(ViewFeedbackEESy.this, "No Report Found", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @SuppressLint({"SetTextI18n", "NewApi"})
    private void fetchReport() {
        String date = etDate.getText().toString().trim();
        String name=etName.getText().toString();
        if (date.isEmpty()) {
            Toast.makeText(this, "Please enter a Date and text", Toast.LENGTH_SHORT).show();
            return;
        }

        // Clear
        tableReport.removeViews(1, tableReport.getChildCount() - 1); // Remove all rows except the header

        Cursor cursor = dbHelper.getReportsByDaterRaw(date,name);


            if (cursor != null && cursor.moveToFirst()) {
                int rowCount = 1; // Initialize a counter for SrNo
                do {
                    // Get column indices
                    int subInfoIndex = cursor.getColumnIndex(DBFeedbackHelper.COLUMN_SUBJECT);
                    int nameIndex = cursor.getColumnIndex(DBFeedbackHelper.COLUMN_NAME);
                    int feedbackInfoIndex = cursor.getColumnIndex(DBFeedbackHelper.COLUMN_FEEDBACK);
                    int dateIndex=cursor.getColumnIndex(DBFeedbackHelper.COLUMN_DATE);


                    // Check if indices are valid
                    if (subInfoIndex == -1 || feedbackInfoIndex == -1) {
                        Toast.makeText(this, "Error: One or more columns do not exist in the database.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Fetch data using valid indices
                    String subinfo = cursor.getString(subInfoIndex);
                    name1=cursor.getString(nameIndex);
                    date1=cursor.getString(dateIndex);
                    String feedbackinfo = cursor.getString(feedbackInfoIndex);

                    // Create a new ro
                    newRow = new TableRow(this);
                    newRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    // Create TextViews for each piece of data
                    tvStudInfo = createCell(subinfo);
                    tvGetStudinfo = createCell(feedbackinfo);


                    // Add TextViews to the new row
                    newRow.addView(tvStudInfo);
                    newRow.addView(tvGetStudinfo);

                    // Add the new row to the table
                    tableReport.addView(newRow);
                    newRow.setVisibility(View.VISIBLE);

                    rowCount++; // Increment the counter for the next row
                } while (cursor.moveToNext());

                // Show the table

                    // Show the table
                    tableReport.setVisibility(View.VISIBLE);



        } else {
            Toast.makeText(this, "No report found for this date", Toast.LENGTH_SHORT).show();
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



}