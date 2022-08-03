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

    private MapPageFragment mapPageFragment;
    private MainPageFragment mainPageFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //hide Action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        mapPageFragment = new MapPageFragment();
        mainPageFragment = new MainPageFragment();
        myPageFragment = new MyPageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, mainPageFragment).commit();

        binding.ButtonLogOut.setOnClickListener(v->{
            Log.d(TAG, "LogOut");
            mAuth.signOut();
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.map:
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, mapPageFragment).commit();
                    return true;
                case R.id.main:
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, mainPageFragment).commit();
                    return true;
                case R.id.mypage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, myPageFragment).commit();
                    return true;
            }
            return false;
        });
    }
}