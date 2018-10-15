package com.example.jieyuwang.myapplication.rxjava;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jieyuwang.myapplication.R;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by Evan (JieYu.Wang) on 2018/10/11.
 */

public class RxjavaSecActivity extends FragmentActivity implements View.OnClickListener {
    public static final String TAG = "Rx222";

    /**
     * Button
     */
    private Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mLifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);;
        initView();
    }

    private void initView() {
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button2:
                onSend();
                break;
        }
    }
    private LifecycleProvider<Lifecycle.Event> mLifecycleProvider;



    private void onSend() {

        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
               /* Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();*/
                Log.d(TAG, "current requested " + emitter.requested());

                for (int i = 0; ; i++) {
                    Log.d(TAG, "emit " + i);
                    Log.d(TAG, "current requested " + emitter.requested());
                    //Thread.sleep(10);
                    emitter.onNext(i);
                }

            }
        }, BackpressureStrategy.BUFFER);
        int bu = upstream.bufferSize();
        Log.d(TAG, "bufferSize " + bu);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            private Subscription mSubscription;

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
//                s.request(2);  //注意这句代码
                mSubscription = s;
                s.request(96);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
                mSubscription.request(128);

            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        upstream.compose(RxHelper.<Integer>applySchedulers())
                .compose(mLifecycleProvider.<Integer>bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .compose(mLifecycleProvider.<Integer>bindToLifecycle())
                .subscribe(downstream);

    }
}
