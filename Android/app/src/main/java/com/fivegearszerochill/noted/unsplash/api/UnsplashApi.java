package com.fivegearszerochill.noted.unsplash.api;

import com.fivegearszerochill.noted.unsplash.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UnsplashApi {
    @GET("/photos")
    Call<List<Photo>> getPhotos();
}
