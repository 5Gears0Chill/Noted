package com.fivegearszerochill.noted.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.repository.NotebookRepository;
import com.fivegearszerochill.noted.repository.OnNotebookInsertedCall;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;

public class NotebookViewModel extends AndroidViewModel {
    private NotebookRepository repository;

    public NotebookViewModel(@NonNull Application application) {
        super(application);
        repository = new NotebookRepository(application);
    }

    public void insertNotebook(NotebookEntity notebook, @NonNull OnNotebookInsertedCall insertedCall) {
        repository.insertNewNotebookAsync(notebook, insertedCall);
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotebooks() {
        return repository.getPaginatedNotebooksAsync();
    }

    public void deleteNotebook(NotebookEntity notebook) {
        repository.deleteNotebook(notebook);
    }


    public LiveData<NotebookEntity> getNotebook(long notebookId) {
        return repository.getNotebookById(notebookId);
    }
}
