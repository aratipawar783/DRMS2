package com.example.drms2.admin.adminEJ.adminEJFY;
import com.example.drms2.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.drms2.DatabaseHelper;

import java.io.ByteArrayOutputStream;
public class AdminFYEJNewReport extends AppCompatActivity implements View.OnClickListener {

    int i;

    EditText et1,et2,et3,et4,et6,et5,et7,et8,et9,et11,et12,et13,et10,et15,et20,et14,et16,et17,et18,et19,et21,et22,et23,et24,et25;
    private RatingBar etRating1, etRating2, etRating3, etRating4, etRating5;
    TextView tvSrNo1,tvSrNo2,tvSrNo3,tvSrNo4,tvSrNo5,tvSrNo6,tvSrNo7,tvSrNo8;
    TextView tvRollNo,tvEnroll,tvStudentName,tvAbsentStudents,tvDate,tvCC,tvHOD,tvBranch;
    EditText etRollNo,etStudentName,etAbsentStudents,etDate,etCC,etHOD,etBranch,etEnroll;
    TextView tvSession1,tvSession2,tvSession3,tvSession4,tvSession5,tvSession6,tvSession7,tvSession8,tvShortBreak,tvLunchBreak;
    private DatabaseHelper dbHelper;
    TextView[] textViewArray;
    EditText[] editGetStudInfo;
    private ImageView screenshotImageView;
    private Bitmap screenshot;
    TextView notificationTextView;



    private CropViewEJFY cropView;


    TextView[] textSessionArray,textStudInfo;
    Button btnAgree,btnConvert,btnSend;


    private EditText[] editTextSubjectName,editTextSubjectTeacher,editTextAdjusted,editTextSyllabus;
    private RatingBar[] ratingBars;
    Button clearNotificationButton,takeScreenshotButton,sendToSecondActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_fyejnew_report);
        btnAgree=findViewById(R.id.btnSave);
        btnAgree.setOnClickListener(this);

        takeScreenshotButton = findViewById(R.id.takeScreenshotButton);
        takeScreenshotButton.setOnClickListener(this);



        // Reference to the CropView in the layout

        // Initially hide the crop button


        // Initialize views
        editGetStudInfo=new EditText[]{
                etRollNo=findViewById(R.id.etRollNo),
                etEnroll=findViewById(R.id.etEnroll),
                etStudentName=findViewById(R.id.etStudentName),
                etDate=findViewById(R.id.etDate),
                etAbsentStudents=findViewById(R.id.etAbsentStudents),
                etCC=findViewById(R.id.etCC),
                etHOD=findViewById(R.id.etHOD),
                etBranch=findViewById(R.id.etBranch)
        };
        textStudInfo=new TextView[]{
                tvRollNo=findViewById(R.id.tvRollNo),
                tvEnroll=findViewById(R.id.tvEnroll),
                tvStudentName=findViewById(R.id.tvStudentName),
                tvDate=findViewById(R.id.tvDate),
                tvAbsentStudents=findViewById(R.id.tvAbsentStudents),
                tvCC=findViewById(R.id.tvCC),
                tvHOD=findViewById(R.id.tvHOD),
                tvBranch=findViewById(R.id.tvBranch)
        };
        etRating1 = findViewById(R.id.etRating1);
        etRating2 = findViewById(R.id.etRating2);
        etRating3 = findViewById(R.id.etRating3);
        etRating4 = findViewById(R.id.etRating4);
        etRating5 = findViewById(R.id.etRating5);
        tvShortBreak=findViewById(R.id.tvShortBreak);
        tvLunchBreak=findViewById(R.id.tvLunchBreak);

        // Initialize EditText array
        editTextSubjectName = new EditText[] {



                et1 = findViewById(R.id.et1),
                et5=findViewById(R.id.et5),
                et9=findViewById(R.id.et9),
                et13 = findViewById(R.id.et13),
                et17 = findViewById(R.id.et17),
                et19 = findViewById(R.id.et19),
                et21 = findViewById(R.id.et21)

        };
        editTextSubjectTeacher=new EditText[]{
                et2 = findViewById(R.id.et2),
                et6 = findViewById(R.id.et6),
                et10=findViewById(R.id.et10),
                et14 = findViewById(R.id.et14),
                et18 = findViewById(R.id.et18),
                et22 = findViewById(R.id.et22)

        };
        textViewArray=new TextView[]{
                tvSrNo1=findViewById(R.id.tvSrNo1),
                tvSrNo2=findViewById(R.id.tvSrNo2),
                tvSrNo3=findViewById(R.id.tvSrNo3),
                tvSrNo4=findViewById(R.id.tvSrNo4),
                tvSrNo5=findViewById(R.id.tvSrNo5),
                tvSrNo6=findViewById(R.id.tvSrNo6),
                tvSrNo7=findViewById(R.id.tvSrNo7),
                tvSrNo8=findViewById(R.id.tvsrNo8)
        };
        editTextAdjusted=new EditText[]{
                et3 = findViewById(R.id.et3),
                et7 = findViewById(R.id.et7),
                et11 = findViewById(R.id.et11),
                et19 = findViewById(R.id.et19),
                et23 = findViewById(R.id.et23),
                et15=findViewById(R.id.et15)

        };
        editTextSyllabus=new EditText[]{
                et4 = findViewById(R.id.et4),
                et20=findViewById(R.id.et20),
                et8 = findViewById(R.id.et8),
                et12 = findViewById(R.id.et12),
                et16 = findViewById(R.id.et16),
                et24 = findViewById(R.id.et24),
                et20=findViewById(R.id.et20)

        };
        textSessionArray=new TextView[]{
                tvSession1=findViewById(R.id.tvSession1),
                tvSession2=findViewById(R.id.tvSession2),
                tvSession3=findViewById(R.id.tvSession3),
                tvSession4=findViewById(R.id.tvSession4),
                tvSession5=findViewById(R.id.tvSession5),
                tvSession6=findViewById(R.id.tvSession6),
                tvSession7=findViewById(R.id.tvSession7),
                tvSession8=findViewById(R.id.tvSession8)
        };
        // Initialize RatingBar array
        ratingBars = new RatingBar[] {
                etRating1,
                etRating2,
                etRating3,
                etRating4,
                etRating5
        };




        // Crop the image when the button is clicked


        // Send the screenshot and show notification


        // Initialize database helper
        dbHelper = new DatabaseHelper(this);
    }
    public void onClick(View view) {



        if(view.getId()==R.id.takeScreenshotButton)
        {
            View rootView = getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            screenshot = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);

            if (screenshot != null) {
                Log.d("FirstActivity", "Screenshot captured successfully.");
                // Convert the Bitmap to a byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                screenshot.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // Send the screenshot to the MiddleActivity
                Intent intent = new Intent(AdminFYEJNewReport.this,MiddleActivityEJFY.class);
                intent.putExtra("screenshotfyej", byteArray);
                startActivity(intent);
            } else {
                Log.e("FirstActivity", "Failed to capture screenshot.");
            }
        }

        if(view.getId()==R.id.btnSave) {
            // Get data from EditTexts
            String strRoll = etRollNo.getText().toString();
            String strDate=etDate.getText().toString();
            // Assuming the first EditText is for the session

            // Get ratings from RatingBars
            String[] studInfo = getTextViewStudInfo();
            String[] getStudInfo = getEditTextGetStudInfoValues();
            String[] ratings = getRatingBarValues();
            String[] syllabusContents = getEditTextSyllabusValues();
            String[] subjectTeacher = getEditTextSubjectTeacherValues();
            String[] subjectName = getEditTextSubjectNameValues();
            String[] session = getTextViewSessionValues();
            String[] adjustedConducted = getEditTextAdjustedValues();
            String[] srNo = getTextViewSrNoValues();


            // Loop through the EditText values and save them in the database
            for (int i = 0; i <= 7; i++) {

                // Modify as neede// Insert report data into the database
                if (i == 2) {
                    String tvf2 = tvShortBreak.getText().toString();
                    String tv2 = tvShortBreak.getText().toString();
                    String tv1 = tv2.replace(tvShortBreak.getText(), "-");
                    String tv3 = tvShortBreak.getText().toString();
                    String tvf3 = tv3.replace(tvShortBreak.getText(), "-");
                    String tv4 = tvShortBreak.getText().toString();
                    String tvf4 = tv4.replace(tvShortBreak.getText(), "-");
                    String tv5 = tvShortBreak.getText().toString();
                    String tvf5 = tv5.replace(tvShortBreak.getText(), "-");
                    if(strRoll.startsWith("EJ1")) {
                        dbHelper.insertReport(strRoll, studInfo[i % studInfo.length], getStudInfo[i % getStudInfo.length], srNo[i % srNo.length], session[i % session.length], tv1, tvf2, tvf3, tvf4, tvf5, strDate);
                    }else {
                        Toast.makeText(this, "Report failed to save ", Toast.LENGTH_SHORT).show();
                    }
//
                } else if (i == 5) {
                    String tvf22 = tvLunchBreak.getText().toString();
                    String tv22 = tvLunchBreak.getText().toString();
                    String tv12 = tv22.replace(tvLunchBreak.getText(), "-");
                    String tv32 = tvLunchBreak.getText().toString();
                    String tvf32 = tv32.replace(tvLunchBreak.getText(), "-");
                    String tv42 = tvLunchBreak.getText().toString();
                    String tvf42 = tv42.replace(tvLunchBreak.getText(), "-");
                    String tv52 = tvLunchBreak.getText().toString();
                    String tvf52 = tv52.replace(tvLunchBreak.getText(), "-");
                    if(strRoll.startsWith("EJ1")) {
                        dbHelper.insertReport(strRoll, studInfo[i % studInfo.length], getStudInfo[i % getStudInfo.length], srNo[i % srNo.length], session[i % session.length], tv12, tvf22, tvf32, tvf42, tvf52, strDate);
                    }else {

                        Toast.makeText(this, "Report failed to save ", Toast.LENGTH_SHORT).show();

                    }
//                Toast.makeText(this, "Reports saved successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    if(strRoll.startsWith("EJ1")) {
                        dbHelper.insertReport(strRoll, studInfo[i % studInfo.length], getStudInfo[i % getStudInfo.length], srNo[i % srNo.length], session[i % session.length], subjectName[i % subjectName.length], subjectTeacher[i % subjectTeacher.length], adjustedConducted[i % adjustedConducted.length], syllabusContents[i % syllabusContents.length], ratings[i % ratings.length], strDate); // Use the rating based on index

                    }
                    else {

                        Toast.makeText(this, "Report failed to save ", Toast.LENGTH_SHORT).show();
                        // Show a success message
                    }
//                Toast.makeText(this, "Reports saved successfully!", Toast.LENGTH_SHORT).show();
                }


            }
            Toast.makeText(this, "Reports saved successfully!", Toast.LENGTH_SHORT).show();

        }
    }



    // Method to get values from EditTexts
    public String[] getEditTextSubjectNameValues() {
        String[] values = new String[editTextSubjectName.length];
        for (i = 0; i < editTextSubjectName.length; i++) {
            values[i] = editTextSubjectName[i].getText().toString();
        }
        return values;
    }
    public String[] getEditTextGetStudInfoValues() {
        String[] values = new String[editGetStudInfo.length];
        for (i = 0; i < editGetStudInfo.length; i++) {
            values[i] = editGetStudInfo[i].getText().toString();
        }
        return values;
    }
    public String[] getTextViewStudInfo() {
        String[] values = new String[textStudInfo.length];
        for (i = 0; i <textStudInfo.length; i++) {
            values[i] =textStudInfo[i].getText().toString();
        }
        return values;
    }
    public String[] getEditTextSubjectTeacherValues() {
        String[] values = new String[editTextSubjectTeacher.length];
        for (i = 0; i < editTextSubjectTeacher.length; i++) {
            values[i] = editTextSubjectTeacher[i].getText().toString();
        }
        return values;
    }
    public String[] getEditTextAdjustedValues() {
        String[] values = new String[editTextAdjusted.length];
        for (i = 0; i < editTextAdjusted.length; i++) {
            values[i] = editTextAdjusted[i].getText().toString();
        }
        return values;
    }
    public String[] getEditTextSyllabusValues() {
        String[] values = new String[editTextSyllabus.length];
        for ( i = 0; i < editTextSyllabus.length; i++) {
            values[i] = editTextSyllabus[i].getText().toString();
        }
        return values;
    }
    public String[] getTextViewSessionValues() {
        String[] values = new String[textSessionArray.length];
        for (i = 0; i < textSessionArray.length; i++) {
            values[i] = textSessionArray[i].getText().toString();
        }
        return values;
    }
    public String[] getTextViewSrNoValues() {
        String[] values = new String[textViewArray.length];
        for (i = 0; i < textViewArray.length; i++) {
            values[i] = textViewArray[i].getText().toString();
        }
        return values;
    }

    // Method to get values from RatingBars
    public String[] getRatingBarValues() {
        String[] values = new String[ratingBars.length];
        String[] values1 = values;
        for (i = 0; i < ratingBars.length; i++) {
            values1[i] = String.valueOf(ratingBars[i].getRating());
        }
        return values1;
    }

}
