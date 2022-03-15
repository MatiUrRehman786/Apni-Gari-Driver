package com.buzzware.apnigaridriver.activities.support;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.buzzware.apnigaridriver.activities.BaseNavDrawer;
import com.buzzware.apnigaridriver.databinding.ActivitySupportBinding;

public class Support extends BaseNavDrawer {

    ActivitySupportBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBinding = ActivitySupportBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        init();

        setListener();
    }

    private void setListener() {

        mBinding.include2.menuIV.setOnClickListener(view -> openCloseDrawer());

    }

    private void init() {

        mBinding.include2.appBarTitle.setText("Support");

    }

}
