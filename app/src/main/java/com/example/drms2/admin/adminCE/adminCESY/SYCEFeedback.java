package com.example.drms2.admin.adminCE.adminCESY;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.DBFeedbackHelper;
import com.example.drms2.R;

public class SYCEFeedback extends AppCompatActivity  {

    int i;

    EditText et1, et2, et3, et4, et6, et5, et7, et8, et9, et11, et12, et10,etDate,etName;

    TextView tvSubject, tvFeedback;

    private DBFeedbackHelper dbHelper;
    EditText[] edittextArray1;
    EditText[] edittextArray2;


    Button btnSaveFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        btnSaveFeedback=findViewById(R.id.btnSaveFeedback);

        // Initialize views
        edittextArray1 = new EditText[]{
                et1 = findViewById(R.id.et1),
                et3 = findViewById(R.id.et3),
                et5 = findViewById(R.id.et5),
                et7 = findViewById(R.id.et7),
                et9 = findViewById(R.id.et9),
                et11 = findViewById(R.id.et11),

        };
        edittextArray2 = new EditText[]{
                et2 = findViewById(R.id.et2),
                et4 = findViewById(R.id.et4),
                et6 = findViewById(R.id.et6),
                et8 = findViewById(R.id.et8),
                et10 = findViewById(R.id.et10),
                et12 = findViewById(R.id.et12),

        };

        etDate=findViewById(R.id.etDate);
        etName=findViewById(R.id.etName);

        // Initialize database helper
        dbHelper = new DBFeedbackHelper(SYCEFeedback.this);
        btnSaveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] subjectinfo = getSubjectInfo();
                String[] feedbackInfo = getFeedbackInfo();
                String date=etDate.getText().toString();
                String name=etName.getText().toString();
                // Loop through the EditText values and save them in the database
                for (int i = 0; i < 6; i++) {
                    // Modify as neede// Insert report data into the database

                    dbHelper.insertReport(subjectinfo[i % subjectinfo.length], feedbackInfo[i % feedbackInfo.length],date,name); // Use the rating based on index
                    // Show a success message
//                Toast.makeText(this, "Reports saved successfully!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SYCEFeedback.this, "Feedback saved successfully!", Toast.LENGTH_LONG).show();


            }
        });

    }



    // Get data from EditTex
    // Assuming the first EditText is for the session

    // Get ratings from RatingBars



    // Method to get values from EditTexts
    public String[] getSubjectInfo() {
        String[] values = new String[edittextArray1.length];
        for (i = 0; i < edittextArray1.length; i++) {
            values[i] = edittextArray1[i].getText().toString();
        }
        return values;
    }

    public String[] getFeedbackInfo() {
        String[] values = new String[edittextArray2.length];
        for (i = 0; i < edittextArray2.length; i++) {
            values[i] = edittextArray2[i].getText().toString();
        }
        return values;
    }
}

