package com.example.jieyuwang.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hcb.formvalidate.Condition;
import com.hcb.formvalidate.DefaultErrorWay;
import com.hcb.formvalidate.FormValidator;
import com.hcb.formvalidate.validator.MaxValidator;
import com.hcb.formvalidate.validator.MinValidator;
import com.hcb.formvalidate.validator.RequiredValidator;

/**
 * Created by Evan (JieYu.Wang) on 2018/8/24.
 */

public class FragStepOne extends Fragment implements View.OnClickListener {
    public MainActivity mActivity;

    private View view;
    /**
     * 下一步
     */
    private Button mBtnNext;
    private EditText mEtInput;
    private AutoCompleteTextView mFirstLable;
    private EditText mEtInput3;
    private EditText mEtInput2;

    public FragStepOne() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        view = View.inflate(getActivity(), R.layout.fragment_one, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO:OnCreateView Method has been created, run FindViewById again to generate code
        initView(view);
        return view;
    }

    public void initView(View view) {
        mFirstLable = (AutoCompleteTextView) view.findViewById(R.id.tv_first_label);
        mFirstLable.setClickable(false);
        mFirstLable.setEnabled(false);
        mEtInput = (EditText) view.findViewById(R.id.et_input);
        mEtInput2 = (EditText) view.findViewById(R.id.et_input2);
        mEtInput3 = (EditText) view.findViewById(R.id.et_input3);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);

        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_next:
//                mFirstLable.setText(mEtInput.getText());
                FormValidator formValidator = new FormValidator();
                formValidator.initErrorHandle(new DefaultErrorWay(getContext()))

                        .add(new MinValidator(mEtInput, 6),
                                new RequiredValidator(mEtInput2, "请输入2"),
                                new MinValidator(mEtInput2, 6),
                                new RequiredValidator(mEtInput3, "请输入3"),
                                new MinValidator(mEtInput3, 6),
                                new MaxValidator(mFirstLable, null, 8))
                        .startValidation();
                if (formValidator.isAllValid()) {


                    FragStepTwo stepTwo = new FragStepTwo();
               /* FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .(stepTwo, FragStepTwo.class.getSimpleName()).commitNow();*/
                    mActivity.switchFragment(stepTwo);

                }
                break;
        }
    }
}
