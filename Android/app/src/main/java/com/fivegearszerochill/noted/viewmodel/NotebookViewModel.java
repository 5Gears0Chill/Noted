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
    private NotebookRepository notebookRepository;

    public NotebookViewModel(@NonNull Application application) {
        super(application);
        notebookRepository = new NotebookRepository(application);
    }

    public void insertNotebook(NotebookEntity notebook, @NonNull OnNotebookInsertedCall insertedCall) {
        notebookRepository.insertNewNotebook(notebook, insertedCall);
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotebooks() {
        return notebookRepository.getPaginatedNotebooksAsync();
    }

    public void deleteNotebook(NotebookEntity notebook) {
        notebookRepository.deleteNotebook(notebook);
    }


}
