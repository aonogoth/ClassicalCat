package com.classical.aono.classicalcat.http;

import android.support.annotation.NonNull;

import com.classical.aono.classicalcat.common.AppClient;
import com.classical.aono.classicalcat.domain.Book;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

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

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    private List<Book> getBooks() {
        Map<String, String > params = new HashMap<>();
        params.put("q",key);
        params.put("start","0");
        params.put("end","50");
        try {
            BookResponse bookResponse = AppClient.httpService.getBooks(params).execute().body();
            return bookResponse.getBooks();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


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
