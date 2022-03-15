package com.buzzware.apnigaridriver.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.buzzware.apnigaridriver.activities.BaseActivity;
import com.buzzware.apnigaridriver.activities.auth.vm.AuthViewModel;
import com.buzzware.apnigaridriver.activities.home.Home;
import com.buzzware.apnigaridriver.activities.yourTrip.YourTrip;
import com.buzzware.apnigaridriver.databinding.ActivityLoginBinding;
import com.buzzware.apnigaridriver.generic.GenericModelLiveData;
import com.buzzware.apnigaridriver.utils.AlertUtils;

import java.util.prefs.BackingStoreException;

public class Login extends BaseActivity {

    ActivityLoginBinding binding;

    AuthViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setListeners();

        setView();

        init();


    }

    private void setView() {

        binding.appBar.appBarTitle.setText("Sign in");

        binding.signUpTV.setText(Html.fromHtml("Don’t have an account? <font color=\"#1B76C0\">SIGN UP</font>"));

    }

    private void setListeners() {

        binding.signUpTV.setOnClickListener(view -> moveToSignUpActivity());

        binding.logInTV.setOnClickListener(view -> {

//            login();

            startActivity(new Intent(Login.this, YourTrip.class));

            finish();

        });
        binding.appBar.backIV.setOnClickListener(view -> {

            finish();
        });

        binding.tvForgot.setOnClickListener(v -> showForgetPassword());

    }


    private void moveToSignUpActivity() {

        startActivity(new Intent(Login.this, SignUp.class));

        finish();
    }


    void init() {

        model = new ViewModelProvider(this).get(AuthViewModel.class);

        model.getAuthLiveData().observe(Login.this, this::handleAuth);

        model.getForgetPasswordLiveData().observe(Login.this, this::handleForgetPassword);
    }

    private void handleForgetPassword(GenericModelLiveData genericModelLiveData) {

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

                Toast.makeText(this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                break;
        }
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

        startActivity(new Intent(Login.this, YourTrip.class));

        finish();
    }

    void showForgetPassword() {

        AlertUtils.showSingleInputDialog(Login.this,
                "Reset Password",
                "Email",
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                "Send",
                "Cancel",
                new AlertUtils.SingleInputDialogListener() {
                    @Override
                    public void positiveCallback(String input) {

                      //  validateAndSendEmail(input);

                    }

                    @Override
                    public void negativeCallback() {

                        // ...

                    }
                });

    }

    private void validateAndSendEmail(String input) {

        if (!input.isEmpty()) {

            model.sendForgetPasswordEmail(input);

        } else {

            showErrorAlert("Email Required");

        }
    }


    private void login() {

        if (validate()) {

            String email = binding.emailET.getText().toString();

            String password = binding.passwordET.getText().toString();

            model.authenticateWithEmailAndPassword(email, password, Login.this);

        }

    }

    private boolean validate() {

        if (binding.emailET.getText().toString().isEmpty()) {

            showErrorAlert("Email Required");

            return false;
        }

        if (binding.passwordET.getText().toString().isEmpty()) {

            showErrorAlert("Password Required");

            return false;
        }

        return true;
    }

}
