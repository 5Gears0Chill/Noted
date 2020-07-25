package com.fivegearszerochill.noted.unsplash.api;

import com.fivegearszerochill.noted.unsplash.models.Photo;
import com.fivegearszerochill.noted.unsplash.response.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UnsplashApi {
    @GET("/photos")
    Call<List<Photo>> getPhotos();

    @GET("/search/photos")
    Call<SearchResponse<Photo>> getPhotos(@QueryMap Map<String, Object> params);
}
