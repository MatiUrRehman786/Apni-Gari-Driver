package com.buzzware.apnigaridriver.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzware.apnigaridriver.activities.BaseActivity;
import com.buzzware.apnigaridriver.activities.auth.Login;
import com.buzzware.apnigaridriver.activities.auth.SignUp;
import com.buzzware.apnigaridriver.activities.auth.vm.mo.User;
import com.buzzware.apnigaridriver.activities.yourTrip.YourTrip;
import com.buzzware.apnigaridriver.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setListeners();

        inIt();

    }

    private void inIt() {

        if (!getUserId().isEmpty()) {

            startActivity(new Intent(SplashActivity.this, YourTrip.class));

            finish();

        }

    }


    private void setListeners() {

        binding.loginTV.setOnClickListener(view -> moveToLoginActivity());
        binding.signUpTV.setOnClickListener(view -> moveToSignUpActivity());

    }

    private void moveToLoginActivity() {

        startActivity(new Intent(SplashActivity.this, Login.class));

        finish();
    }

    private void moveToSignUpActivity() {

        startActivity(new Intent(SplashActivity.this, SignUp.class));

        finish();
    }
}