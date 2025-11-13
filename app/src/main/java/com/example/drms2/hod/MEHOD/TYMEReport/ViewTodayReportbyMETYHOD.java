package com.example.drms2.hod.MEHOD.TYMEReport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

import java.io.File;

public class ViewTodayReportbyMETYHOD extends AppCompatActivity {

    private ImageView imageView; // ImageView to display the cropped image
    private Button clearButton; // Button to clear the displayed image
    private Button sendButton; // Button to send the image to the next activity
    private SharedPreferences sharedPreferences; // SharedPreferences to retrieve the image URI

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_today_reportby_metyhod);

        imageView = findViewById(R.id.imageView_cropped);
        clearButton = findViewById(R.id.button_clear);
        sendButton = findViewById(R.id.button_send);

        sharedPreferences = getSharedPreferences("ScreenshotApptyme", MODE_PRIVATE);

        // Load the cropped image from the saved file
        String imagePath = sharedPreferences.getString("imageUritymeForMiddle", null);
        if (imagePath != null) {
            File file = new File(imagePath);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
            }
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearImage(); // Clear the displayed image
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageToNextActivity(); // Save the image URI to SharedPreferences without navigating
            }
        });
    }

    private void clearImage() {
        imageView.setImageBitmap(null); // Clear the ImageView
        imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
    }

    private void sendImageToNextActivity() {
        String imagePath = sharedPreferences.getString("imageUritymeForMiddle", null);
        if (imagePath != null) {
            Uri imageUri = Uri.fromFile(new File(imagePath));
            // Save the image URI to SharedPreferences for future use
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imageUritymeprincipalForThird", imageUri.toString());
            editor.apply();

            // Just save the URI without starting the next activity
            Toast.makeText(this, "Image saved for next activity. You can choose when to navigate.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Image file does not exist.", Toast.LENGTH_SHORT).show();
        }
    }
}
