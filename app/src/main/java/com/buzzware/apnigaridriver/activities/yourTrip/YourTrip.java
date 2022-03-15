package com.buzzware.apnigaridriver.activities.yourTrip;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.buzzware.apnigaridriver.activities.BaseNavDrawer;
import com.buzzware.apnigaridriver.activities.yourTrip.adapter.YourTripAdapter;
import com.buzzware.apnigaridriver.activities.yourTrip.adapter.YourTripAdapterUpComming;
import com.buzzware.apnigaridriver.databinding.ActivityYourTripsBinding;
import com.google.android.material.tabs.TabLayout;

public class YourTrip extends BaseNavDrawer {

    ActivityYourTripsBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBinding = ActivityYourTripsBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        init();

        setListener();
    }

    private void setListener() {

        mBinding.include.menuIV.setOnClickListener(view -> openCloseDrawer());

        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().toLowerCase().equals("completed")) {
                    mBinding.listRV.setLayoutManager(new LinearLayoutManager(YourTrip.this));

                    mBinding.listRV.setAdapter(new YourTripAdapter());
                } else {
                    mBinding.listRV.setLayoutManager(new LinearLayoutManager(YourTrip.this));

                    mBinding.listRV.setAdapter(new YourTripAdapterUpComming());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void init() {

        mBinding.include.appBarTitle.setText("Your Trip");

        mBinding.listRV.setLayoutManager(new LinearLayoutManager(this));

        mBinding.listRV.setAdapter(new YourTripAdapter());

    }

}
