package com.example.evan.evantestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.evan.evantestapplication.bean.Shoe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    CoffeeMaker mMaker;
    @Inject
    Shoe mShoe;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.tv_text);
        registerListeners();

        /* TestApplicationComponent build = DaggerTestApplicationModule.builder().build();
        build.inject(getApplication());
        MainComponent mainComponent = DaggerMainComponent.builder().build();
        mainComponent.inject(this);*/

    }

    private void registerListeners() {
        Log.d("test", "" + mButton.hashCode());
        mButton.setOnClickListener(v -> {
//            mMaker.brew();
            mTextView.setText(mShoe.toString());
            gotoSecondActivity();

        });

    }

    private void gotoSecondActivity() {
        startActivity(new Intent(this, SecondActivity.class));

    }
}
