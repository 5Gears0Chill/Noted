package com.fivegearszerochill.noted.unsplash.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.unsplash.com/";
    private static final String CLIENT_ID = "Client-ID OgTP2jTSA2jC7iYQeMe1nRkjOW0ZSaUmi0BuXj0IaRA";
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();


    /**
     * @return retrofit build instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request request = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", CLIENT_ID)
                        .build();
                return chain.proceed(request);
            }).addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    /**
     * @param serviceClass physical class used for instance
     * @param <TService>   Type of Retrofit Defined interface to create as a service
     * @return service instance
     */
    public static <TService> TService createService(Class<TService> serviceClass) {
        return getRetrofitInstance().create(serviceClass);
    }
}
