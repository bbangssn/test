package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.test.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //hide Action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        binding.ButtonLogOut.setOnClickListener(v->{
            Log.d(TAG, "LogOut");
            mAuth.signOut();
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        });
    }
}