package com.example.evan.evantestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.evan.evantestapplication.bean.Shoe;

import javax.inject.Inject;

/**
 * Created by Evan (JieYu.Wang) on 2018/2/7.
 */

public class SecondActivity extends Activity {
    TextView mTextView;
    @Inject
    Shoe mShoe;

    @Inject
    Shoe mTShoe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        mTextView =
                (TextView) findViewById(R.id.text);
        TestApplication.getShoeComponent().inject(this);
       /* DaggerSecondComponent.builder().build().inject(this);
        TestApplicationComponent build = DaggerTestApplicationModule.builder().build();
        build.inject(getApplication());*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView.setText(mShoe.toString() + "\n" + mTShoe.toString());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
