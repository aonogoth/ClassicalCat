package com.classical.aono.classicalcat.http;

import android.support.annotation.NonNull;

import com.classical.aono.classicalcat.common.AppClient;
import com.classical.aono.classicalcat.domain.Book;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/9/27.
 */

public class BooksSupplier implements Supplier<Result<List<Book>>> {

    public String key;

    public void setKey(String key) {
        this.key = key;
    }

    public BooksSupplier(String key) {
        this.key = key;
    }


    OkHttpClient client = new OkHttpClient();

    private static final String BASE_URL = "http://118.178.95.56:8084/api/aono/";//"http://118.178.95.56:8084/api/aono/GetWorkList";//"https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private List<Book> getBooks() {
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
            JSONArray jaBooks = json.optJSONArray("books");
            Gson gson = new Gson();
            List<Book> books = gson.fromJson(jaBooks.toString(), new TypeToken<List<Book>>() {
            }.getType());
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private List<Book> getBooks() {
//        Map<String, String > params = new HashMap<>();
//        params.put("Category",key);
////        params.put("q",key);
////        params.put("start","0");
////        params.put("end","50");
//        try {
//            BookResponse bookResponse = AppClient.httpService.getBooks(params).execute().body();
//            return bookResponse.getBooks();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @NonNull
    @Override
    public Result<List<Book>> get() {
        List<Book> books = getBooks();
        if (books == null) {
            return Result.failure();
        } else {
            return Result.success(books);
        }
    }
}
