package com.classical.aono.classicalcat.http;

import android.support.annotation.NonNull;
import android.util.Log;

import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.domain.Work;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gotha on 2017/9/29.
 */

public class WorksSupplier implements Supplier<Result<List<Work>>> {

    public String key;

    public void setKey(String key) {
        this.key = key;
    }

    public WorksSupplier(String key) {
        this.key = key;
    }


    OkHttpClient client = new OkHttpClient();

    private static final String BASE_URL = "http://118.178.95.56:8084/api/aono/";//"http://118.178.95.56:8084/api/aono/GetWorkList";//"https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private List<Work> getBooks() {
        HttpUrl url = HttpUrl.parse(getAbsoluteUrl("GetWorkList"))
                .newBuilder()
                .addQueryParameter("Category",key)
//                .addQueryParameter("q", key)
//                .addQueryParameter("start", "0")
//                .addQueryParameter("end", "50")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            //Log.e("Hehe",json.toString());
            JSONArray jaBooks = json.optJSONArray("data");
            Gson gson = new Gson();
            List<Work> works = gson.fromJson(jaBooks.toString(), new TypeToken<List<Work>>() {
            }.getType());
            return works;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @NonNull
    @Override
    public Result<List<Work>> get() {
        List<Work> works = getBooks();
        if (works == null) {
            return Result.failure();
        } else {
            return Result.success(works);
        }
    }
}
