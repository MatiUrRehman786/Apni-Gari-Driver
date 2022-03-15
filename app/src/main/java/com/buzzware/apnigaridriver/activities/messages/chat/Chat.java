package com.buzzware.apnigaridriver.activities.messages.chat;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.buzzware.apnigaridriver.activities.BaseActivity;
import com.buzzware.apnigaridriver.databinding.ActivityMessagesBinding;

public class Chat extends BaseActivity {

    ActivityMessagesBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBinding = ActivityMessagesBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        init();

        setListener();
    }

    private void setListener() {

        mBinding.include.backIV.setOnClickListener(view -> finish());

    }

    private void init() {

        mBinding.include.appBarTitle.setText("Messages");

//        mBinding.listRV.setLayoutManager(new LinearLayoutManager(this));

//        mBinding.listRV.setAdapter(new ChatListAdapter());
    }

}
