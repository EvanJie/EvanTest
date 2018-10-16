package com.example.jieyuwang.myapplication.http;

import com.example.jieyuwang.myapplication.bean.Blog;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Evan (JieYu.Wang) on 2018/10/16.
 */

public interface HttpResponse {
    @GET("orgs/octokit/repos")
    Flowable<String> getBlogs();
}
