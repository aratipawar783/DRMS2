package com.example.drms2.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

public class PrincipalDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_branch);

    }

    public void btnComputerClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.CMOptionActivity.class);
        startActivity(intent);
    }

    public void btnElectronicClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.EJOptionActivity.class);
        startActivity(intent);
    }

    public void btnElectricalClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.EEoptionActivity.class);
        startActivity(intent);
    }

    public void btnCivilClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.CEOptionActivity.class);
        startActivity(intent);
    }

    public void btnMechanicalClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.MEOptionActivity.class);
        startActivity(intent);
    }

    public void btnAutomobileClick(View view) {
        Intent intent=new Intent(PrincipalDashboardActivity.this,com.example.drms2.principal.AEOptionActivity.class);
        startActivity(intent);
    }
}