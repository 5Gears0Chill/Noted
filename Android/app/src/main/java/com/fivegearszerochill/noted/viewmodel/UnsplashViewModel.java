package com.fivegearszerochill.noted.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fivegearszerochill.noted.repository.UnsplashRepository;
import com.fivegearszerochill.noted.unsplash.models.Photo;

import java.util.List;

public class UnsplashViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> photos;
    private UnsplashRepository repository;

    public void init() {
        if(repository != null) {
            return;
        }
        repository = UnsplashRepository.getInstance();
    }

    public LiveData<List<Photo>> getPhotos() {
        photos = repository.getPhotos();
        return photos;
    }
}
