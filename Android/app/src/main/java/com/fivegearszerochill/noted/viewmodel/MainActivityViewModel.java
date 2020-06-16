package com.fivegearszerochill.noted.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.repository.NotebookRepository;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;

public class MainActivityViewModel extends AndroidViewModel {
    private NotebookRepository notebookRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        notebookRepository = new NotebookRepository(application);
    }

    public void insertNotebook(NotebookEntity notebook) {
        notebookRepository.insertNewNotebook(notebook);
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotes() {
        return notebookRepository.getPaginatedNotesAsync();
    }


}
