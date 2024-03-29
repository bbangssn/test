package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.test.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;//firebase Authentication helper
    private ActivitySignUpBinding binding;//view binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //hide action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        binding.ButtonSignUp.setOnClickListener(v -> signUp());
    }

    private void signUp() {
        String email = binding.EditTextEmail.getText().toString();
        String password = binding.EditTextPassword.getText().toString();
        String passwordCheck = binding.EditTextPasswordCheck.getText().toString();

        if (email.length() > 0 && password.length() > 0){
            if (password.equals(passwordCheck))
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                Intent intent = new Intent();
                                intent.putExtra("email", binding.EditTextEmail.getText().toString());
                                intent.putExtra("password", binding.EditTextPassword.getText().toString());
                                setResult(RESULT_OK, intent);
                                finish();//UI when succeed
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //UI when Fail
                            }
                        });
            else
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "입력을 완료해주세요!", Toast.LENGTH_SHORT).show();
    }
}