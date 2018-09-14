package com.example.jieyuwang.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.jieyuwang.myapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Evan (JieYu.Wang) on 2018/9/5.
 */

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<User>> users;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
        AsyncTask<Void, Void, List<User>> task = new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<User> arrayList = new ArrayList<>();
                for (int i = 0; i < 5; i++
                        ) {
                    User user = new User();
                    user.age = i + 15;
                    user.name = i + "AAA";
                    arrayList.add(user);

                }
                return arrayList;
            }

            @Override
            protected void onPostExecute(List<User> result) {

                users.postValue(result);
            }
        };
        Void[] a = null;
        task.execute(a);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
