package com.example.smartcropapp.soil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcropapp.R;

public class SoilActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private SoilClassifier classifier;
    private ImageView imageView;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);
        imageView = findViewById(R.id.imageView);
        resultView = findViewById(R.id.resultView);

        try {
            classifier = new SoilClassifier(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button pickButton = findViewById(R.id.pickButton);
        Button reportButton = findViewById(R.id.reportButton);

        pickButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        reportButton.setOnClickListener(v -> {
            if (lastPrediction != null) {
                String path = ReportGenerator.generateReport(this, lastPrediction.soilType, lastPrediction.confidence);
                resultView.setText("Report saved: " + path);
            }
        });
    }

    private SoilClassifier.Prediction lastPrediction;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                lastPrediction = classifier.classify(bitmap);
                resultView.setText("Soil: " + lastPrediction.soilType + "\nConfidence: " + (lastPrediction.confidence * 100) + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
