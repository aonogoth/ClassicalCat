package com.classical.aono.classicalcat.http;

import android.support.annotation.NonNull;
import android.util.Log;

import com.classical.aono.classicalcat.domain.ResultOut;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gotha on 2017/11/6.
 */

public class AboutSupplier implements Supplier<Result<ResultOut>>  {

    public String key;

    public void setKey(String key) {
        this.key = key;
    }

    public AboutSupplier(String key) {
        this.key = key;
    }

    OkHttpClient client = new OkHttpClient();

    private static final String BASE_URL = "http://118.178.95.56:8084/api/aono/";//"http://118.178.95.56:8084/api/aono/GetWorkList";//"https://api.douban.com/v2/";


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }



    private ResultOut toSend() {
        HttpUrl url = HttpUrl.parse(getAbsoluteUrl("SendFeedback"))
                .newBuilder()
                .build();
        Log.e("urlurl",url.toString());
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("sendContent", key);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.e("response",response.body().toString());
            JSONObject json = new JSONObject(response.body().string());
            //Log.e("Hehe",json.toString());
            //JSONArray jaBooks = json.optJSONArray("data");
            Gson gson = new Gson();
            ResultOut ro = gson.fromJson(json.get("data").toString(), new TypeToken<ResultOut>() {
            }.getType());
            return ro;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    @Override
    public Result<ResultOut> get() {
        ResultOut works = toSend();
        if (works == null) {
            return Result.failure();
        } else {
            return Result.success(works);
        }
    }
}
