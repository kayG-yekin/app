package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class forgot_pass extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText email;
    private Button resetPass;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.user_email);
        resetPass = (Button)findViewById(R.id.user_submit);
        progressBar = (ProgressBar)findViewById(R.id.progressBar3);

        resetPass.setOnClickListener(v -> resetPassword());

    }

    private void resetPassword(){
        String user_email = email.getText().toString().trim();
        if(user_email.isEmpty()){
            email.setError("Email cannot be empty");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            email.setError("Please provide valid email.");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(user_email).
                addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"Try again! Something wrong happened!",Toast.LENGTH_LONG).show();
                    }
                });
    }
}