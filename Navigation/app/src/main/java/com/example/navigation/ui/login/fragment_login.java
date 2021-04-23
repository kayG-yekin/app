package com.example.navigation.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.navigation.R;
import com.example.navigation.dashboard;
import com.example.navigation.forgot_pass;
import com.example.navigation.ui.gallery.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class fragment_login extends Fragment implements  View.OnClickListener{
    private FirebaseAuth mAuth;

    private TextView forget;
    private EditText password,userEmail;
    private Button login;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        userEmail = (EditText)root.findViewById(R.id.user_email);
        password = (EditText)root.findViewById(R.id.user_password);
        progressBar = (ProgressBar)root.findViewById(R.id.progressBar2);
        forget = (TextView)root.findViewById(R.id.forget);
        login = (Button)root.findViewById(R.id.user_submit);

        forget.setOnClickListener(this);
        login.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget:
                startActivity(new Intent(getActivity(), forgot_pass.class));
                break;
            case R.id.user_submit:
                registration();
                break;
        }
    }

    private void registration() {
        String user_email = userEmail.getText().toString().trim();
        String user_pass = password.getText().toString().trim();

        if(user_email.isEmpty()){
            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            userEmail.setError("Please provide valid email.");
            userEmail.requestFocus();
            return;
        }
        if(user_pass.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(user_email,user_pass)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(),"Login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), dashboard.class));
                    }else{
                        Toast.makeText(getActivity(),"Failed to login",Toast.LENGTH_LONG).show();
                    }
                });
    }
}