package com.example.drms2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_dashboard);

    }

    public void btnAdminClick(View view) {
        Intent intent=new Intent(MainDashboardActivity.this, com.example.drms2.admin.LoginActivity.class);
        startActivity(intent);
    }

    public void btnHodClick(View view) {
        Intent intent=new Intent(MainDashboardActivity.this,com.example.drms2.HODLoginActivity.class);
        startActivity(intent);
    }

    public void btnPrincipalClick(View view) {
        Intent intent=new Intent(MainDashboardActivity.this,com.example.drms2.PrincipalLoginActivity.class);
        startActivity(intent);
    }


}