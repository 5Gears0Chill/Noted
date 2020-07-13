package com.fivegearszerochill.noted.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.repository.NoteRepository;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;

public class RecentNotesViewModel extends AndroidViewModel {

    private NoteRepository repository;

    public RecentNotesViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public LiveData<PagedList<NoteAndNotebook>> getRecentNotesPaged() {
        return repository.getRecentNotesPaginatedAsync();
    }
}