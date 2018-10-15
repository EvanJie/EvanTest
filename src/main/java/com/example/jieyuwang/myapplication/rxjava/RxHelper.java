package com.example.jieyuwang.myapplication.rxjava;

import android.app.Activity;
import android.app.Dialog;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {
    private Activity mActivity;
    private Dialog mProgressDialog;

    public RxHelper(Activity activity) {
        mActivity = activity;
    }

    public static <Upstream> FlowableTransformer<Upstream, Upstream> applySchedulers() {
        return new FlowableTransformer<Upstream, Upstream>() {
            @Override
            public Publisher apply(Flowable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <Upstream> FlowableTransformer<Upstream, Upstream> showDialog(final String msg) {
        return new FlowableTransformer<Upstream, Upstream>() {
            @Override
            public Publisher<Upstream> apply(Flowable<Upstream> upstream) {
                return upstream
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                if (mActivity != null && mProgressDialog == null) {
                                    mProgressDialog = ProgressDialogHelper.showProgressDialog(mActivity, msg);
                                }
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                ProgressDialogHelper.dismissProgressDialog(mProgressDialog);
                            }
                        });
            }
        };
    }
}