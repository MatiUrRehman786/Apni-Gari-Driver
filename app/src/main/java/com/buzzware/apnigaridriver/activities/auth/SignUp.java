package com.buzzware.apnigaridriver.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.buzzware.apnigaridriver.activities.BaseActivity;
import com.buzzware.apnigaridriver.activities.auth.vm.AuthViewModel;
import com.buzzware.apnigaridriver.activities.auth.vm.mo.User;
import com.buzzware.apnigaridriver.activities.home.Home;
import com.buzzware.apnigaridriver.activities.yourTrip.YourTrip;
import com.buzzware.apnigaridriver.databinding.ActivitySignUpBinding;
import com.buzzware.apnigaridriver.generic.GenericModelLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends BaseActivity {

    ActivitySignUpBinding binding;

    AuthViewModel model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setListeners();

        setView();

        init();


    }

    private void setView() {

        binding.appBar.appBarTitle.setText("Registration");

        binding.signInTV.setText(Html.fromHtml("Already have an account? <font color=\"#1B76C0\">SIGN IN</font>"));

    }

    private void setListeners() {

        binding.signInTV.setOnClickListener(view -> moveToLoginActivity());
        binding.signUpBtn.setOnClickListener(view ->
        {

            signUp();

        });

        binding.appBar.backIV.setOnClickListener(view -> {

            finish();
        });

    }

    private void moveToLoginActivity() {

        startActivity(new Intent(SignUp.this, Login.class));

        finish();
    }

    private void signUp() {

        if (validate()) {

            User user = getUser();

            model.createUserWithEmailAndPassword(user, SignUp.this);

        }

    }

    private User getUser() {

        User user = new User();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        return;

                    }

                    String token = task.getResult();

                    user.token = token;

                });

        user.homeAddress = "";
        user.workAddress = "";
        user.email = binding.emailET.getText().toString();
        user.firstName = binding.firstNameET.getText().toString();
        user.lastName = binding.lastNameET.getText().toString();
        user.phoneNumber = binding.phoneNumberET.getText().toString();
        user.password = binding.passwordET.getText().toString();
        user.city = "";
        user.state = "";
        user.zipcode = "";
        user.userRole = "driver";

        return user;
    }


    private boolean validate() {

        if (binding.firstNameET.getText().toString().isEmpty()) {

            showErrorAlert("First Name Required");

            return false;
        }

        if (binding.lastNameET.getText().toString().isEmpty()) {

            showErrorAlert("Last Name Required");

            return false;
        }

        if (binding.emailET.getText().toString().isEmpty()) {

            showErrorAlert("Email Required");

            return false;
        }

        if (binding.phoneNumberET.getText().toString().isEmpty()) {

            showErrorAlert("Phone Required");

            return false;
        }

        if (binding.passwordET.getText().toString().isEmpty()) {

            showErrorAlert("Password Required");

            return false;
        }

        return true;
    }


    void init() {

        model = new ViewModelProvider(this).get(AuthViewModel.class);

        model.getAuthLiveData().observe(SignUp.this, this::handleAuth);

    }

    private void handleAuth(GenericModelLiveData genericModelLiveData) {

        switch (genericModelLiveData.status) {

            case error:

                hideLoader();

                showErrorAlert(genericModelLiveData.errorMsg);

                break;

            case loading:

                showLoader();

                break;

            case success:

                hideLoader();

                onAuthSucceeded();

                break;
        }
    }

    private void onAuthSucceeded() {

        startActivity(new Intent(SignUp.this, YourTrip.class));

        finish();

    }


}
