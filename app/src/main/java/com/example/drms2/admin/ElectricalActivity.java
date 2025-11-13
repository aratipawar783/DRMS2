package com.example.drms2.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

public class ElectricalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);

        Button buttonShowDialog = findViewById(R.id.button_show_dialog);
        buttonShowDialog.setOnClickListener(v -> showDialog());
        int color= Color.parseColor("#9E71E5F4");
        buttonShowDialog.setBackgroundColor(color);
    }

    private void showDialog() {
        String[] options = {"First Year", "Second Year", "Third Year"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0) {
                            // Handle the selected option
                            Toast.makeText(ElectricalActivity.this, options[0] + " selected", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ElectricalActivity.this, com.example.drms2.admin.adminEE.adminEEFY.AdminEEFYOptions.class);
                            startActivity(intent);
                        }
                        if(which==1) {
                            // Handle the selected option
                            Toast.makeText(ElectricalActivity.this, options[1] + " selected", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ElectricalActivity.this,  com.example.drms2.admin.adminEE.adminEESY.AdminEESYOptions.class);
                            startActivity(intent);
                        }
                        if(which==2) {
                            // Handle the selected option
                            Toast.makeText(ElectricalActivity.this, options[2] + " selected", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ElectricalActivity.this,  com.example.drms2.admin.adminEE.adminEETY.AdminEETYOptions.class);
                            startActivity(intent);
                        }

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}