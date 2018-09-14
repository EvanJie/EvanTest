package com.example.jieyuwang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Evan (JieYu.Wang) on 2018/8/24.
 */

public class FragStepThree extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 提交
     */
    private Button mBtnSave;
    private MainActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

        view = View.inflate(getActivity(), R.layout.fragment_three, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO:OnCreateView Method has been created, run FindViewById again to generate code
        initView(view);
        return view;
    }

    public void initView(View view) {
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_save:
                startActivity(new Intent(getActivity(), ActivityTow.class));
                break;
        }
    }
}
