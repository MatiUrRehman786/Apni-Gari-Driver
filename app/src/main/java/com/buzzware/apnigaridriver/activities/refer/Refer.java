package com.buzzware.apnigaridriver.activities.refer;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.buzzware.apnigaridriver.activities.BaseNavDrawer;
import com.buzzware.apnigaridriver.databinding.ActivityReferBinding;
import com.buzzware.apnigaridriver.databinding.ActivityWalletBinding;

public class Refer extends BaseNavDrawer {

    ActivityReferBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBinding = ActivityReferBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        init();

        setListener();
    }

    private void setListener() {

        mBinding.includeAppBar.menuIV.setOnClickListener(view -> openCloseDrawer());

    }

    private void init() {

        mBinding.includeAppBar.appBarTitle.setText("Refer and Earn");

    }

}
