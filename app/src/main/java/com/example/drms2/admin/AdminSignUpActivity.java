package com.example.drms2.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.DatabaseHelper;
import com.example.drms2.R;

public class AdminSignUpActivity extends AppCompatActivity {


    EditText etxtUser,etxtPassword,etxtConfirmPassword;
    String username="",password="",confirm="";
    Button btnAdminSignUp;
    DatabaseHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);
        etxtUser =findViewById(R.id.etxtUser);
        etxtPassword=findViewById(R.id.etxtPassword);
        btnAdminSignUp=findViewById(R.id.btnAdminSign);
        etxtConfirmPassword=findViewById(R.id.etxtConfirmPassword);
        btnAdminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etxtUser.getText().toString();
                password = etxtPassword.getText().toString();
                confirm=etxtConfirmPassword.getText().toString();
                if (username.equals("")) {
                    etxtUser.setError("Please enter username");
                    etxtUser.requestFocus();
                    return;
                }
                if (password.equals("")) {
                    etxtPassword.setError("Please enter password");
                    etxtPassword.requestFocus();
                    return;
                }
                if (confirm.equals("")) {
                    etxtConfirmPassword.setError("Please Re-enter password");
                    etxtConfirmPassword.requestFocus();
                    return;
                }

                boolean b=dbHelper.validateAdmin(username,confirm);
                if (b && (password.equals(confirm))) {
                    Toast.makeText(AdminSignUpActivity.this, "You have already created an account...!", Toast.LENGTH_SHORT).show();
                    etxtConfirmPassword.setText("");
                    etxtPassword.setText("");
                    etxtUser.setText("");
                }else {
                    boolean b1 = dbHelper.insertAdminSignup(username, confirm);
                    if (b1 && (password.equals(confirm))) {
                        Toast.makeText(AdminSignUpActivity.this, "Sign_up Successful...!", Toast.LENGTH_SHORT).show();
                        etxtConfirmPassword.setText("");
                        etxtPassword.setText("");
                        etxtUser.setText("");
                        Intent intent=new Intent(AdminSignUpActivity.this, BranchActivity.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(AdminSignUpActivity.this, "Sign-up Unsuccessful..!", Toast.LENGTH_SHORT).show();
                        etxtUser.setError("Please enter valid username");
                        etxtUser.requestFocus();
                        etxtPassword.setError("Please enter valid  password");
                        etxtPassword.requestFocus();
                        etxtConfirmPassword.setError("Please Re-enter password");
                        etxtConfirmPassword.requestFocus();
                        etxtConfirmPassword.setText("");
                        etxtPassword.setText("");
                        etxtUser.setText("");
                    }

                }
            }
        });

        dbHelper=new DatabaseHelper(this);
    }


}
