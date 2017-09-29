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
       // @GET("https://api.douban.com/v2/book/search")
       @GET("http://118.178.95.56:8084/api/aono/GetWorkList")
        Call<BookResponse> getBooks(@QueryMap Map<String, String> options);


    }

    public static HttpService httpService;

    public static void initAppClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(new Converter.Factory() {
                    Gson gson = new Gson();

                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, final Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, Object>() {
                            @Override
                            public Object convert(ResponseBody value) throws IOException {
                                try {
                                    if (annotations.length > 1) {
                                        for (Annotation annotation : annotations) {
                                            if (annotation instanceof ConverterName && ((ConverterName) annotation).value().equals("string")) {
                                                return value.string();
                                            }
                                        }
                                    }
                                    return gson.getAdapter(TypeToken.get(type)).fromJson(value.charStream());
                                } finally {
                                    value.close();
                                }
                            }
                        };
                    }

                    @Override
                    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
                        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
                    }

                    @Override
                    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return super.stringConverter(type, annotations, retrofit);
                    }
                })
                .build();
        httpService = retrofit.create(HttpService.class);
    }

}
