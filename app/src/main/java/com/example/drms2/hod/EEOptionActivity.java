package com.example.drms2.hod; // Change this to your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

public class EEOptionActivity extends AppCompatActivity {

    private String[] menuItems = {"First Year", "Second Year","Third Year"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeoption);

        ListView menuListView = findViewById(R.id.menuListView);

        // Create an ArrayAdapter to display the menu items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        menuListView.setAdapter(adapter);

        // Set an item click listener for the ListView
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Handle New Report menu item click
                    Intent intent = new Intent(EEOptionActivity.this,com.example.drms2.hod.EleHOD.FYEEReport.FyEEOptionActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    // Handle History menu item click
                    Intent intent = new Intent(EEOptionActivity.this, com.example.drms2.hod.EleHOD.SYEEReport.SyEEOptionActivity.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    // Handle History menu item click
                    Intent intent = new Intent(EEOptionActivity.this, com.example.drms2.hod.EleHOD.TYEEReport.TyEEOptionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}



