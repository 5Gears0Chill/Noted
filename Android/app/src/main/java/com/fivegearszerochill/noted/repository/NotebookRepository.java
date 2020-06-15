package com.fivegearszerochill.noted.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.room.dao.NotebookDao;
import com.fivegearszerochill.noted.room.database.NotedDatabase;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.util.repository.ExecutorHelper;
import com.fivegearszerochill.noted.util.repository.PagingHelper;
import com.fivegearszerochill.noted.util.repository.TaskHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class NotebookRepository {
    private NotebookDao notebookDao;
    private ExecutorService executor;

    public NotebookRepository(Application application) {
        NotedDatabase database = NotedDatabase.getInstance(application);
        notebookDao = database.getNoteBookDao();
        executor = ExecutorHelper.getSingleThreadExecutorInstance();
    }

    public LiveData<PagedList<NotebookEntity>> getPaginatedNotesAsync() {
        DataSource.Factory<Integer, NotebookEntity> dataSource = notebookDao.getPagedNotebooks();

        return new LivePagedListBuilder<>(
                dataSource,
                PagingHelper.getPagingConfig())
                .setFetchExecutor(ExecutorHelper.getSingleThreadExecutor())
                .build();
    }

    public boolean insertNewNotebook(final NotebookEntity notebook) throws ExecutionException, InterruptedException {
        Future<Long> task = executor.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return notebookDao.addNewNoteBook(notebook);
            }
        });
        if(task.isDone() && task.get()!= null){
            return TaskHelper.SUCCESS;
        }
        return TaskHelper.FAILURE;
    }


}
