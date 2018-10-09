package com.example.jieyuwang.myapplication.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jieyuwang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Evan (JieYu.Wang) on 2018/10/9.
 */

public class RxjavaMainActivity extends FragmentActivity implements View.OnClickListener {
    public static final String TAG = "RxJavaStudy";
    /**
     * TextView
     */
    private TextView mTextView;
    /**
     * Send
     */
    private Button mButton;
    /**
     * Send Always
     */
    private Button mButton1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
//                sendEvent();
                zipObservable();
                break;

            case R.id.button1:
                circleSend();
                break;
        }
    }

    private void circleSend() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {   //无限循环发事件
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w(TAG, throwable);
                    }
                });
    }

    void sendEvent() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
//                emitter.onComplete();
               /* emitter.onError(new Throwable("AAAA"));
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit 5");
                emitter.onNext(5);*/
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(i + "I am value " + integer);
                }
                return Observable.fromIterable(list).delay(1000, TimeUnit.MILLISECONDS);

            }
        })
                /*.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "this is result" + integer;
            }
        })*/.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        Log.d(TAG, "onNext:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "run");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "is disposed:--->" + disposable.isDisposed());
                    }
                });
              /*  .subscribe(new Observer<Integer>() {
                    private Disposable mDisposable;
                    private int mIndex;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "subscribe");
                        Log.d(TAG, "isDisposed:-->" + d.isDisposed());
                        mDisposable = d;
                        Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext thread is :" + Thread.currentThread().getName());
                        Log.d(TAG, "onNext:" + value);
                        mIndex++;
                        if (mIndex == 2) {
                            mDisposable.dispose();
                        }
                        Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "complete");
                    }


                });*/
    }

    void zipObservable() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Thread.sleep(1000);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Thread.sleep(1000);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Thread.sleep(1000);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Thread.sleep(1000);
                Log.d(TAG, "emit complete1");
                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Thread.sleep(1000);
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Thread.sleep(1000);
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Thread.sleep(1000);
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });


    }

}