package com.example.noteapp.base;

import android.content.Context;

import com.example.noteapp.model.User;
import com.example.noteapp.remote.MainApi;
import com.example.noteapp.util.PreferenceManger;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit mRetrofit;

    private static Retrofit provideRetrofit(Context context) {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://api.note.annyong.store")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient(context))
                    .build();
        }
        return mRetrofit;
    }

    private static OkHttpClient provideOkHttpClient(Context context) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .addInterceptor(provideAccessTokenInterceptor(context))
                .build();
    }

    private static Interceptor provideAccessTokenInterceptor(Context context) {
        return chain -> {
            Request original = chain.request();

            HttpUrl originalUrl = original.url();

            HttpUrl.Builder urlBuilder = originalUrl.newBuilder();
            HttpUrl url = urlBuilder.build();

            Request.Builder requestBuilder = original.newBuilder().url(url);

            if (context != null) {
                String access_token = (String) PreferenceManger.getInstance(context).getValue(String.class, "accessToken", "");

                if (!access_token.isEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer " + access_token);
                }
            }
            Request request = requestBuilder.build();
            Response response = chain.proceed(request);

            return createSuccessResponse(response, chain, requestBuilder, context);
        };
    }

    private static Response createSuccessResponse(Response response, Interceptor.Chain chain, Request.Builder requestBuilder, Context context) {

        if (response.code() == 401) {
            PreferenceManger preferenceManger = PreferenceManger.getInstance(context);
            String email = (String) preferenceManger.getValue(String.class, "email", "");
            String password = (String) preferenceManger.getValue(String.class, "password", "");
            if (!email.isEmpty() && !password.isEmpty()) {
                User user = new User(email, password);
                try {
                    retrofit2.Response<User> loginResponse = ApiService.provideApi(MainApi.class, context).login(user).execute();
                    if (loginResponse.code() == 200) {
                        user = loginResponse.body();
                        preferenceManger.setValue("accessToken", user.getAccessToken());
                        requestBuilder.removeHeader("Authorization");
                        requestBuilder.addHeader("Authorization", "Bearer " + user.getAccessToken());
                        response.close();
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;

    }


    public static <T> T provideApi(Class<T> services, Context context) {
        return provideRetrofit(context).create(services);
    }

}
