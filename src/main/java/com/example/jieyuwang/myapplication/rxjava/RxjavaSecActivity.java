package com.example.jieyuwang.myapplication.rxjava;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jieyuwang.myapplication.R;
import com.example.jieyuwang.myapplication.bean.Blog;
import com.example.jieyuwang.myapplication.http.BlogServices;
import com.example.jieyuwang.myapplication.http.HttpResponse;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Evan (JieYu.Wang) on 2018/10/11.
 */

public class RxjavaSecActivity extends FragmentActivity implements View.OnClickListener {
    public static final String TAG = "Rx222";

    /**
     * Button
     */
    private Button mButton2;
    private LifecycleProvider<Lifecycle.Event> mLifecycleProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mLifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);
        ;
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
//                onSend();
//                getStatus();
                getResponse();
                break;
        }
    }

    private void getStatus() {
        Retrofit retrofit = getRetrofit();


        BlogServices blogServices = retrofit.create(BlogServices.class);
        blogServices.getBlogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        Log.i(TAG, "onSubscribe RetrofitAAA");
                    }
                })
                .subscribe(new Subscriber<List<Blog>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.i(TAG, "onSubscribe Retrofit");
                        s.request(100);
                    }

                    @Override
                    public void onNext(List<Blog> listResult) {
                        Log.i(TAG, "onSubscribe Retrofit onNext");
                        if (listResult != null) {
                            for (int i = 0; i < listResult.size(); i++) {

                                Log.i(TAG, "Blog--->" + listResult.get(i).full_name);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onSubscribe Retrofit onError" + "--->" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onSubscribe Retrofit onComplete");
                    }
                });
    }

    @NonNull
    private Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();
        return new Retrofit.Builder()
//                .baseUrl("https://developer.github.com")
                .baseUrl("https://api.github.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


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


    private void getResponse() {
        Retrofit retrofit = getRetrofit();
        HttpResponse httpResponse = retrofit.create(HttpResponse.class);
        httpResponse.getBlogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    private Subscription mS;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mS = s;
                        mS.request(96);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onSubscribe Retrofit onNext");
                        Log.i(TAG, "Blog--->" + s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
