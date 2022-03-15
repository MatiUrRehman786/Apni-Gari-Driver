package com.buzzware.apnigaridriver.activities.setting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.buzzware.apnigaridriver.R;
import com.buzzware.apnigaridriver.activities.BaseNavDrawer;
import com.buzzware.apnigaridriver.activities.auth.Login;
import com.buzzware.apnigaridriver.activities.auth.vm.mo.User;
import com.buzzware.apnigaridriver.activities.profile.ProfileActivity;
import com.buzzware.apnigaridriver.databinding.ActivitySettingBinding;
import com.buzzware.apnigaridriver.databinding.ActivitySupportBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends BaseNavDrawer {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivitySettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setListener();

       // setUserData();
    }

    private void setUserData() {

        DocumentReference users = FirebaseFirestore.getInstance().collection("Users").document(getUserId());

        users.addSnapshotListener((value, error) -> {

            if (value != null) {

                Log.d("UserData", "setUserData: " + value.toString());

                User user = value.toObject(User.class);

                if (user.image != null) {

                    Glide.with(SettingActivity.this).load(user.image).apply(new RequestOptions().centerCrop()).into(binding.userIV);

                }

                binding.nameTV.setText(user.firstName + " " + user.lastName);

                binding.balanceTV.setText(String.valueOf(user.balance));

                if(user.token!=null && !user.token.isEmpty()){

                    binding.notificationSW.setChecked(true);

                }else{

                    binding.notificationSW.setChecked(false);

                }

            }

        });

    }


    private void setFireBaseToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        return;

                    }

                    String token = task.getResult();

                    addTokenToDB(token);

                });
    }

    private void addTokenToDB(String token) {

        Map<String, Object> userData = new HashMap<>();
        userData.put("token", token);

        FirebaseFirestore.getInstance().collection("Users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .update(userData);
    }

    private void setListener() {

        binding.includeAppBar.menuIV.setOnClickListener(view -> openCloseDrawer());
        binding.accountCL.setOnClickListener(v -> {
            startActivity(new Intent(SettingActivity.this, ProfileActivity.class));
        });
        binding.logoutCL.setOnClickListener(v -> {

        //    FirebaseAuth.getInstance().signOut();

            startActivity(new Intent(SettingActivity.this, Login.class));

        });

//        binding.notificationSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                if (b) {
//                    setFireBaseToken();
//                } else {
//                    addTokenToDB("");
//                }
//
//            }
//        });

    }

    private void init() {

        binding.includeAppBar.appBarTitle.setText("Setting");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();
    }
}