package com.classical.aono.classicalcat.common;

import com.classical.aono.classicalcat.http.BookResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by admin on 2017/9/27.
 */

public class AppClient {
    public interface HttpService {
        @GET("https://api.douban.com/v2/book/search")
        Call<BookResponse> getBooks(@QueryMap Map<String, String> options);


    }

    public static HttpService httpService;

}
