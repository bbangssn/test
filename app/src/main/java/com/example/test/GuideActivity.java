package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.test.databinding.ActivityGuideBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class GuideActivity extends AppCompatActivity {
    private final String TAG = "GuideActivity";

    private ActivityGuideBinding binding;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();

        binding.ButtonCountrySelect.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Country")
                        .setItems(UserInfo.CountryEnum.getCountryList(), (dialog, which) -> binding.EditTextCountry.setText(UserInfo.CountryEnum.getCountryList()[which]))
                        .setPositiveButton("Select", null)
                        .setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
        });

        binding.ButtonNext.setOnClickListener(v -> {
            if(binding.EditTextName.getText() != null && binding.EditTextCountry.getText()!=null){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //TODO coderefectory
                UserInfo userInfo = UserInfo.getInstance();
                userInfo.setCountry(UserInfo.CountryEnum.convertCountryToCountryCode(binding.EditTextCountry.getText().toString()));
                userInfo.setName(binding.EditTextName.getText().toString());

                assert user != null;
                db.collection("users").document(user.getUid())
                        .set(userInfo)//유저정보 올리기 우헤헤
                        .addOnCompleteListener(task -> {
                            Toast.makeText(this, "User Info Upload Succeed!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User Info Upload Succeed!");
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "User Info Upload Fail! :", e);//TODO login 실패시 처리 추가
                        });
                //set 성공시
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }else{
                Toast.makeText(this, "입력을 완료해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}