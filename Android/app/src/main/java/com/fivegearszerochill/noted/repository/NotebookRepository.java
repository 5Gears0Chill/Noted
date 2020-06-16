package com.fivegearszerochill.noted.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.room.dao.NotebookDao;
import com.fivegearszerochill.noted.room.database.NotedDatabase;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.util.mutithreading.BackgroundTask;
import com.fivegearszerochill.noted.util.mutithreading.TaskRunner;
import com.fivegearszerochill.noted.util.repository.ExecutorHelper;
import com.fivegearszerochill.noted.util.repository.PagingHelper;

public class NotebookRepository {
    private NotebookDao notebookDao;
    private TaskRunner taskRunner;

    public NotebookRepository(Application application) {
        NotedDatabase database = NotedDatabase.getInstance(application);
        notebookDao = database.getNoteBookDao();
        taskRunner = new TaskRunner();
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotesAsync() {
        DataSource.Factory<Integer, NotebookEntity> dataSource = notebookDao.getPagedNotebooks();

        return new LivePagedListBuilder<>(
                dataSource,
                PagingHelper.getPagingConfig())
                .setFetchExecutor(ExecutorHelper.getSingleThreadExecutor())
                .build();
    }

    public void insertNewNotebook(final NotebookEntity notebook) {
        taskRunner.executeAsync(new BackgroundTask(notebookDao, notebook), (data) -> {
            if (data != null) {
                //notify UI SUCCESS
            }
            //notify UI Failure
        });
    }


}
