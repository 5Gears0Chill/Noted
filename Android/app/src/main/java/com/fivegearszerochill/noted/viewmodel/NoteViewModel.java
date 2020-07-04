package com.fivegearszerochill.noted.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.repository.NoteRepository;
import com.fivegearszerochill.noted.room.entity.NoteEntity;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public LiveData<PagedList<NoteEntity>> getPaginatedNotes(long notebookId) {
        return repository.getNotesAsync(notebookId);
    }
}
