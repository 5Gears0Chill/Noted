package com.fivegearszerochill.noted.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fivegearszerochill.noted.unsplash.api.UnsplashApi;
import com.fivegearszerochill.noted.unsplash.models.Photo;
import com.fivegearszerochill.noted.unsplash.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnsplashRepository {

    private static UnsplashRepository repository;
    private UnsplashApi api;

    public static UnsplashRepository getInstance() {
        if (repository == null) {
            repository = new UnsplashRepository();
        }
        return repository;
    }

    public UnsplashRepository() {
        api = RetrofitClientInstance.createService(UnsplashApi.class);
    }

    public MutableLiveData<List<Photo>> getPhotos() {
        MutableLiveData<List<Photo>> photos = new MutableLiveData<>();
        api.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    photos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                photos.setValue(null);
            }
        });
        return photos;
    }
}
