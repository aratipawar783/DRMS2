package com.example.drms2.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drms2.R;

public class BranchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_branch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnComputerClick(View view) {
        Intent intent=new Intent(BranchActivity.this, ComputerActivity.class);
        startActivity(intent);
    }

    public void btnElectronicClick(View view) {
        Intent intent=new Intent(BranchActivity.this, ElectronicActivity.class);
        startActivity(intent);
    }

    public void btnElectricalClick(View view) {
        Intent intent=new Intent(BranchActivity.this, ElectricalActivity.class);
        startActivity(intent);
    }

    public void btnCivilClick(View view) {
        Intent intent=new Intent(BranchActivity.this, CivilActivity.class);
        startActivity(intent);
    }

    public void btnMechanicalClick(View view) {
        Intent intent=new Intent(BranchActivity.this, MechanicalActivity.class);
        startActivity(intent);
    }

    public void btnAutomobileClick(View view) {
        Intent intent=new Intent(BranchActivity.this, AutoActivity.class);
        startActivity(intent);
    }
}