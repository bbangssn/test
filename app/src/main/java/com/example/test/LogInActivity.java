package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.databinding.ActivityLogInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private final String TAG = "LogInActivity";

    private FirebaseAuth mAuth;//firebase Authentication helper
    private ActivityLogInBinding binding;//view binding

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK && result.getData() != null){
            binding.EditTextEmail.setText(result.getData().getStringExtra("email"));
            binding.EditTextPassword.setText(result.getData().getStringExtra("password"));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //hide action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        //move to SignUp Activity
        binding.ButtonSignUp.setOnClickListener(v -> signUp());
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

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//존재하는 엑티비티가 있다면 그것을 사용하고 그 위에 쌓인 엑티비티 삭제
                        startActivity(intent);
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

    protected void signUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        launcher.launch(intent);
    }
}