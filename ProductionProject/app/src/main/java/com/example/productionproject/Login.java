package com.example.productionproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextemail,editTextpassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    ProgressBar pBar;

    TextView textViewRegister;
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent1  = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        editTextemail=findViewById(R.id.email);
        editTextpassword=findViewById(R.id.Password);
        btnLogin =findViewById(R.id.btn_Login);
        pBar=findViewById(R.id.progressBar);
        textViewRegister=findViewById(R.id.RegisterNow);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pBar.setVisibility(View.VISIBLE);
                String email,password;
                email=String.valueOf(editTextemail.getText());
                password=String.valueOf(editTextpassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successfully.",Toast.LENGTH_SHORT).show();
                                    Intent intent1  = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}