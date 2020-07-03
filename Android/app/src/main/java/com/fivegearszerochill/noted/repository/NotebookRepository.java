package com.fivegearszerochill.noted.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.room.dao.NotebookDao;
import com.fivegearszerochill.noted.room.database.NotedDatabase;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.util.mutithreading.BackgroundDeleteTask;
import com.fivegearszerochill.noted.util.mutithreading.BackgroundInsertTask;
import com.fivegearszerochill.noted.util.mutithreading.TaskRunner;
import com.fivegearszerochill.noted.util.repository.ExecutorHelper;
import com.fivegearszerochill.noted.util.repository.PagingHelper;

public class NotebookRepository {
    private NotebookDao notebookDao;
    private TaskRunner taskRunner;
    private OnNotebookInsertedCall onNotebookInsertedCall;
    private static final String TAG = "NotebookRepository";

    public NotebookRepository(Application application) {
        NotedDatabase database = NotedDatabase.getInstance(application);
        notebookDao = database.getNoteBookDao();
        taskRunner = new TaskRunner();
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotebooksAsync() {
        DataSource.Factory<Integer, NotebookEntity> dataSource = notebookDao.getPagedNotebooks();

        return new LivePagedListBuilder<>(
                dataSource,
                PagingHelper.getPagingConfig())
                .setFetchExecutor(ExecutorHelper.getSingleThreadExecutor())
                .build();
    }

    public void insertNewNotebook(final NotebookEntity notebook, @NonNull OnNotebookInsertedCall insertedCall) {
        this.onNotebookInsertedCall = insertedCall;
        taskRunner.executeAsync(new BackgroundInsertTask(notebookDao, notebook), (data) -> {
            Log.d(TAG, "insertNewNotebook: data from callback: " + data);
            if (data != null) {
                onNotebookInsertedCall.updateSuccess();
            } else {
                onNotebookInsertedCall.updateFailure();
            }
        });
    }


    public void deleteNotebook(NotebookEntity notebook) {
        taskRunner.executeAsync(new BackgroundDeleteTask(notebookDao, notebook), (data) -> {
        });
    }
}
