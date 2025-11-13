package com.example.drms2.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.R;

public class HistoryLoginActivity extends AppCompatActivity {


    EditText etxtUser,etxtPassword;
    String username="",password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etxtUser =findViewById(R.id.etxtUser);
        etxtPassword=findViewById(R.id.etxtPassword);

    }

    public void btnLogClick(View view) {
        username=etxtUser.getText().toString();
        password=etxtPassword.getText().toString();

        if(username.equals(""))
        {
            etxtUser.setError("Please enter email");
            etxtUser.requestFocus();
            return;
        }
        if(password.equals(""))
        {
            etxtPassword.setError("Please enter password");
            etxtPassword.requestFocus();
            return;
        }
        if(username.equals("admin") && password.equals("admin@123"))
        {
            Toast.makeText(this, "Login Success...!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, FetchReportActivity.class);
            startActivity(intent);
        }
        if(!(username.equals("admin") && password.equals("admin@123"))){
            Toast.makeText(this, "Invalid Credentials..!", Toast.LENGTH_SHORT).show();
        }



    }
}
