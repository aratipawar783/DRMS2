package com.example.drms2.admin.adminAE.adminAESY;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MiddleActivitySYAE extends AppCompatActivity {

    private CropViewSYAE cropView;
    private Bitmap screenshot; // Store the original screenshot
    private Bitmap croppedImage; // Store the cropped image
    private ImageView imageViewCropped; // ImageView to display the cropped image
    private Button clearButton; // Button to clear the cropped image
    private SharedPreferences sharedPreferences; // SharedPreferences to store the image URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_syae);

        cropView = findViewById(R.id.cropView);
        imageViewCropped = findViewById(R.id.imageView_cropped);
        Button cropButton = findViewById(R.id.button_crop);
        Button sendButton = findViewById(R.id.button_send);
        clearButton = findViewById(R.id.button_clear);

        sharedPreferences = getSharedPreferences("ScreenshotAppsyae", MODE_PRIVATE);

        // Check if there's an image passed from the dashboard
        byte[] byteArray = getIntent().getByteArrayExtra("screenshotsyae");
        if (byteArray != null) {
            screenshot = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            cropView.setBitmap(screenshot);
        } else {
            Toast.makeText(this, "No image to display.", Toast.LENGTH_SHORT).show();
        }

        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropView.cropImage(); // This will set the cropped image
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croppedImage != null) {
                    saveImageToFile(croppedImage); // Save the cropped image to a file
                    Toast.makeText(MiddleActivitySYAE.this, "Image saved. You can navigate back to the dashboard.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MiddleActivitySYAE.this, "Please crop the image first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCroppedImage(); // Clear the cropped image
            }
        });
    }

    private void saveImageToFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(null), "cropped_image_middlesyae.png");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            // Save the image URI to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imageUrisyaeForMiddle", file.getAbsolutePath());
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCroppedImage(Bitmap bitmap) {
        this.croppedImage = bitmap; // Store the cropped image
        imageViewCropped.setImageBitmap(croppedImage); // Display the cropped image in ImageView
        cropView.setVisibility(View.GONE); // Hide the CropView
        imageViewCropped.setVisibility(View.VISIBLE); // Show the ImageView
    }

    private void clearCroppedImage() {
        croppedImage = null; // Clear the cropped image
        imageViewCropped.setImageBitmap(null); // Clear the ImageView
        cropView.setVisibility(View.VISIBLE); // Show the CropView again
        imageViewCropped.setVisibility(View.GONE); // Hide the ImageView
        cropView.setBitmap(screenshot); // Reset the CropView with the original screenshot
        Toast.makeText(this, "Cropped image cleared.", Toast.LENGTH_SHORT).show();
    }
}