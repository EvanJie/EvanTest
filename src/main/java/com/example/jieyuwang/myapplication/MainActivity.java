package com.example.jieyuwang.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

import com.example.jieyuwang.myapplication.bean.User;

import java.util.List;

public class MainActivity extends FragmentActivity {
    Fragment mCurrentFragment;
    Fragment mPre;
    private FragmentManager mFm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final TextView hello = (TextView) findViewById(R.id.tv_hello);
        mFm = getSupportFragmentManager();
        FragmentTransaction mFt = mFm.beginTransaction();
        Fragment stepOne = FragStepOne.instantiate(this, FragStepOne.class.getName());
        mFt.add(R.id.fl_container, stepOne);
        mFt.commit();
        mCurrentFragment = stepOne;
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                //todo
                StringBuffer b = new StringBuffer();
                for (User user : users) {

                    Log.w(MainActivity.class.getSimpleName(), user.toString());
                    b.append(user.name).append(',');
                }
                hello.setText(b.toString());

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void switchFragment(Fragment toTag) {
        Fragment to = toTag;

        if (mCurrentFragment != to) {

            FragmentTransaction transaction = mFm.beginTransaction();
            if (!to.isAdded()) {//判断是否被添加到了Activity里面去了
                transaction.hide(mCurrentFragment).add(R.id.fl_container, to).commit();
            } else {
                transaction.hide(mCurrentFragment).show(to).commit();
            }
            mCurrentFragment = to;
        }


    }

    @Override
    public void onBackPressed() {
        if (mFm.getFragments().size() > 1) {
            mFm.beginTransaction().remove(mCurrentFragment);
            mFm.popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}
