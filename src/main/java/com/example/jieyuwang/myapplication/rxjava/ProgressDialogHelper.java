/*
 * Copyright (C) 2018 贵阳货车帮科技有限公司
 */

package com.example.jieyuwang.myapplication.rxjava;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.jieyuwang.myapplication.R;

/**
 * @author 严皓 <hao.yan@56qq.com>
 */

public final class ProgressDialogHelper {

    private ProgressDialogHelper() {
        throw new AssertionError("Don't instance! ");
    }

    /**
     * default value is true of cancelable
     * default value is false of CanceledOnTouchOutside
     */
    public static Dialog showProgressDialog(Activity activity, String progressDialogMsg) {
        return showProgressDialog(activity, progressDialogMsg, true);
    }

    public static Dialog showProgressDialog(Activity activity, String progressDialogMsg, boolean cancelable) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        try {
            Dialog progressDialog = new Dialog(activity, R.style.progress_dialog);
            View progressView = View.inflate(activity, R.layout.custom_progress_dialog, null);
            TextView msgTextView = (TextView) progressView.findViewById(R.id.message);
            msgTextView.setText(progressDialogMsg);
            progressDialog.setContentView(progressView);
            progressDialog.setCancelable(cancelable);
            progressDialog.setCanceledOnTouchOutside(false);
            Window window = progressDialog.getWindow();
            if (null != window) {
                window.setBackgroundDrawableResource(android.R.color.transparent);
            }
            progressDialog.show();
            return progressDialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dismissProgressDialog(Dialog progressDialog) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
