package com.example.jieyuwang.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jieyuwang.myapplication.bean.User;

import java.util.List;

/**
 * Created by Evan (JieYu.Wang) on 2018/9/5.
 */

public class ActivityTow extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_two);
        final TextView viewById = findViewById(R.id.tv_title);
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

                viewById.setText(b.toString());

            }
        });
    }
}
