package com.example.drms2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HODLoginActivity extends AppCompatActivity {


    EditText etxtUser,etxtPassword;
    String username="",password="";
    Button btnAdminSignUp;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etxtUser =findViewById(R.id.etxtUser);
        etxtPassword=findViewById(R.id.etxtPassword);
        btnAdminSignUp=findViewById(R.id.btnAdminSign);
        btnAdminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HODLoginActivity.this,com.example.drms2.hod.HODSignUpActivity.class);
                startActivity(intent);
            }
        });

        dbHelper=new DatabaseHelper(this);
    }

    public void btnLogClick(View view) {
        username=etxtUser.getText().toString();
        password=etxtPassword.getText().toString();

        if(username.equals(""))
        {
            etxtUser.setError("Please enter username");
            etxtUser.requestFocus();
            return;
        }
        if(password.equals(""))
        {
            etxtPassword.setError("Please enter password");
            etxtPassword.requestFocus();
            return;
        }
        boolean b=dbHelper.validateHOD(username,password);
        if(b)
        {
            Toast.makeText(this, "Login Successful..!", Toast.LENGTH_SHORT).show();
            etxtUser.setText("");
            etxtPassword.setText("");
            Intent intent=new Intent(HODLoginActivity.this, com.example.drms2.hod.MainHODDashboardActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Login Unsuccessful..!", Toast.LENGTH_SHORT).show();
            etxtUser.setError("Please enter valid username");
            etxtUser.requestFocus();
            etxtPassword.setError("Please enter valid password");
            etxtPassword.requestFocus();
            etxtUser.setText("");
            etxtPassword.setText("");
        }




    }
}
