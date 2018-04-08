package com.example.evan.evantestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Evan (JieYu.Wang) on 2018/2/7.
 */

public class SecondActivity extends Activity {
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        mTextView =
                (TextView) findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
