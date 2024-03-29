package com.buzzware.apnigaridriver.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GravityCompat;

import com.buzzware.apnigaridriver.activities.auth.Login;
import com.buzzware.apnigaridriver.activities.messages.chatList.ChatList;
import com.buzzware.apnigaridriver.activities.refer.Refer;
import com.buzzware.apnigaridriver.activities.setting.SettingActivity;
import com.buzzware.apnigaridriver.activities.support.Support;
import com.buzzware.apnigaridriver.activities.wallet.Wallet;
import com.buzzware.apnigaridriver.activities.yourTrip.YourTrip;
import com.buzzware.apnigaridriver.databinding.AppBaseLayoutBinding;

public class BaseNavDrawer extends BaseActivity implements View.OnClickListener {

    AppBaseLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = AppBaseLayoutBinding.inflate(getLayoutInflater());

        super.setContentView(binding.getRoot());// The base layout that contains your navigation drawer.

        setBaseListeners();

    }
//
//    void getActiveRide() {
//
//        Query query = FirebaseFirestore.getInstance().collection("Bookings")
//                .whereEqualTo("userId", getUserId())
//                .whereIn("status", Arrays.asList("driverAccepted", "driverReached", "rideStarted", "booked", "reBooked", AppConstants.RideStatus.RIDE_COMPLETED));
//
//        query.get()
//                .addOnCompleteListener(
//                        this::parseBaseSnapshot
//                );
//    }

//    void parseBaseSnapshot(Task<QuerySnapshot> task) {
//
//        RideModel rideModel = null;
//
//        hideLoader();
//
//        if (!task.isSuccessful()) {
//
//            openCloseDrawer();
//
//            showErrorAlert(task.getException().getLocalizedMessage());
//
//            return;
//        }
//
//        if (task.getResult() != null) {
//
//            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                rideModel = document.toObject(RideModel.class);
//
//                rideModel.id = document.getId();
//
//
//                startActivity(new Intent(BaseNavDrawer.this, BookARideActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//
//                finish();
//
//                return;
//
//            }
//
//        }
//
//        hideLoader();
//
//        openCloseDrawer();
//
//        showErrorAlert("No Active Ride Found");
//
//    }

//    private void getCurrentUserData() {
//
//        DocumentReference users = FirebaseFirestore.getInstance().collection("Users").document(getUserId());
//
//        users.addSnapshotListener((value, error) -> {
//
//            if (BaseNavDrawer.this != null && value != null) {
//
//                User user = value.toObject(User.class);
//
//                View headerLayout =
//                        binding.navView.getHeaderView(0);
//
//                if (user == null)
//
//                    return;
//
//                ImageView picIV = headerLayout.findViewById(R.id.picCIV);
//
//                TextView nameTV = headerLayout.findViewById(R.id.nameTV);
//
//               nameTV.setText(user.firstName + " " + user.lastName);
//
//                if (BaseNavDrawer.this != null)
//
//                    try {
//
//                        Glide.with(this).load(user.image).apply(new RequestOptions().centerCrop().placeholder(R.drawable.dummy_girl)).into(picIV);
//
//                    }catch (Exception e) {
//
//
//                  }
//
//            }
//
//
//        });
//
//    }

    protected void setBaseListeners() {

        binding.navView.homeLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, YourTrip.class));

            finish();
        });


        binding.navView.messagesLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, ChatList.class));

            finish();
        });


        binding.navView.yourTripLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, YourTrip.class));

            finish();
        });

        binding.navView.walletLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, Wallet.class));

            finish();
        });

        binding.navView.referLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, Refer.class));

            finish();
        });
        binding.navView.supportLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, Support.class));

            finish();
        });


        binding.navView.settingLL.setOnClickListener(view -> {

            openCloseDrawer();

            startActivity(new Intent(BaseNavDrawer.this, SettingActivity.class));

            finish();
        });


    }

    private void openDriveWithIrideIntent() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.buzzware.iridedriver"));
        startActivity(intent);
    }

    private void logout() {

//        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(BaseNavDrawer.this, Login.class)

                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));

        finish();

    }

    @Override
    public void setContentView(int layoutResID) {
        if (binding.containerFL != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, binding.containerFL, false);
            binding.containerFL.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (binding.containerFL != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            binding.containerFL.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {

        binding.containerFL.addView(view, params);

    }


    @Override
    public void onClick(View v) {
//        if (v == binding.navView.findViewById(R.id.homeLay)) {
//
//            showLoader();
//            getActiveRide();
//
//
//        } else if (v == binding.navView.findViewById(R.id.bookingsLay)) {
//
//            openCloseDrawer();
//
//            startActivity(new Intent(BaseNavDrawer.this, BookingsActivity.class));
//            finish();
//
//        } else if (v == binding.navView.findViewById(R.id.walletLay)) {
//
//            openCloseDrawer();
//
//            startActivity(new Intent(BaseNavDrawer.this, Wallet.class));
//            finish();
//
//        } else if (v == binding.navView.findViewById(R.id.profileLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, Profile.class));
//            finish();
//
//        } else if (v == binding.navView.findViewById(R.id.inviteLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, Invitation.class));
//            finish();
//
//
//        } else if (v == binding.navView.findViewById(R.id.notificationLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, Notifications.class));
//            finish();
//
//
//        } else if (v == binding.navView.findViewById(R.id.aboutUsLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, AboutUs.class));
//
//        } else if (v == binding.navView.findViewById(R.id.csLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, CreateNewRequestActivity.class));
//            finish();
//
//        } else if (v == binding.navView.findViewById(R.id.activeRide)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, BookARideActivity.class));
//            finish();
//        } else if (v == binding.navView.findViewById(R.id.schedulesLay)) {
//
//            openCloseDrawer();
//            startActivity(new Intent(BaseNavDrawer.this, ScheduledRides.class));
//            finish();
//        }
    }

    public void openCloseDrawer() {

        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {

            binding.drawer.closeDrawer(GravityCompat.START);

        } else {

            binding.drawer.openDrawer(GravityCompat.START);

        }
    }

}
