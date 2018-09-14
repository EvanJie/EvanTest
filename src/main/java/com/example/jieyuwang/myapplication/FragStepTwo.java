package com.example.jieyuwang.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Evan (JieYu.Wang) on 2018/8/24.
 */

public class FragStepTwo extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 下一步
     */
    private Button mBtnNext;
    private MainActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

        view = View.inflate(getActivity(), R.layout.fragment_two, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO:OnCreateView Method has been created, run FindViewById again to generate code
        initView(view);
        return view;
    }

    public void initView(View view) {
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_next:
                FragStepThree stepThree = new FragStepThree();

              /*  FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .add(stepThree, FragStepThree.class.getSimpleName()).commitNow();*/
                mActivity.switchFragment(stepThree);
                break;
        }
    }

}
