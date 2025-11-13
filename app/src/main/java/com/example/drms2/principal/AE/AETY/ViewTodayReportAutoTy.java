package com.example.drms2.principal.AE.AETY;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

import java.io.File;

public class ViewTodayReportAutoTy extends AppCompatActivity {

    private ImageView imageView; // ImageView to display the image
    private SharedPreferences sharedPreferences; // SharedPreferences to retrieve the image URI
    private static final String TAG = "ViewTodayReportAETY";
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_today_report_auto_ty);

        // Initialize the ImageView and Button
        imageView = findViewById(R.id.imageView_received); // Ensure the correct ID
        btnClear = findViewById(R.id.button_clear); // Initialize the clear button

        // Retrieve the image URI stored in SharedPreferences
        sharedPreferences = getSharedPreferences("ScreenshotApptyae", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("imageUrityaeprincipalForThird", null);

        Log.d(TAG, "Retrieved image URI: " + imageUriString); // Log the retrieved URI

        if (imageUriString != null) {
            // Convert the string back to a Uri
            Uri imageUri = Uri.parse(imageUriString);
            File file = new File(imageUri.getPath()); // Get the file from the URI

            // Check if the file exists
            if (file.exists()) {
                Log.d(TAG, "Image file exists: " + file.getAbsolutePath()); // Log the file path
                // Decode the image file into a Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    // Set the Bitmap in the ImageView
                    imageView.setImageBitmap(bitmap);
                } else {
                    // Log and show a placeholder if the Bitmap decoding fails
                    Log.d(TAG, "Bitmap decoding failed. The file might not be a valid image.");
                    imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
                }
            } else {
                // Log if the image file does not exist
                Log.d(TAG, "Image file does not exist.");
                imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
            }
        } else {
            // Log and handle the case when no image URI is found in SharedPreferences
            Log.d(TAG, "No image URI found in SharedPreferences.");
            imageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a placeholder image
        }

        // Set an OnClickListener for the btnClear button to clear the image
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearImage();
            }
        });
    }

    // Function to clear the image from the ImageView
    private void clearImage() {
        // Clear the image from the ImageView (set it to null or placeholder)
        imageView.setImageResource(R.drawable.ic_launcher_foreground); // Placeholder image
        // Optionally, you can also clear the SharedPreferences value
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("imageUrityaeprincipalForThird"); // Remove the saved URI
        editor.apply();
        Log.d(TAG, "Image cleared and SharedPreferences value removed.");
    }
}
