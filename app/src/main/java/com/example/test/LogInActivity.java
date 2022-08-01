package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.databinding.ActivityLogInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    final String TAG = "LogInActivity";

    FirebaseAuth mAuth;//firebase Authentication helper
    ActivityLogInBinding binding;//view binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //hide action bar
        getSupportActionBar().hide();

        //move to SignUp Activity
        binding.ButtonSignUp.setOnClickListener(v -> {
            ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                binding.EditTextEmail.setText(result.getData().getStringExtra("email"));
                binding.EditTextPassword.setText(result.getData().getStringExtra("password"));
            });
        });
        binding.ButtonLogIn.setOnClickListener(v -> logIn());
    }

    protected void logIn(){
        String email = binding.EditTextEmail.getText().toString();
        String password = binding.EditTextPassword.getText().toString();

        if(email.length()>0&&password.length()>0)
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                        //UI when succeed
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        //UI when failed
                    }
                });
        else
            Toast.makeText(this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
    }

    protected void SignUp(){

    }
}