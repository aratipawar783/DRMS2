package com.example.drms2.hod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

public class MainHODDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_hoddashboard);

    }

    public void btnCMClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this, com.example.drms2.hod.CMOptionActivity.class);
        startActivity(intent);
    }

    public void btnEJClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this,com.example.drms2.hod.EJOptionActivity.class);
        startActivity(intent);
    }

    public void btnCivilClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this,com.example.drms2.hod.CivilOptionActivity.class);
        startActivity(intent);
    }
    public void btnMeClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this,com.example.drms2.hod.MEOptionActivity.class);
        startActivity(intent);
    }

    public void btnEleClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this,com.example.drms2.hod.EEOptionActivity.class);
        startActivity(intent);
    }

    public void btnAutoClick(View view) {
        Intent intent=new Intent(MainHODDashboardActivity.this,com.example.drms2.hod.AutoOptionActivity.class);
        startActivity(intent);
    }


}