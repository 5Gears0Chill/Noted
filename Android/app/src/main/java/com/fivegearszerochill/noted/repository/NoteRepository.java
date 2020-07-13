package com.fivegearszerochill.noted.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fivegearszerochill.noted.room.dao.NoteDao;
import com.fivegearszerochill.noted.room.database.NotedDatabase;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;
import com.fivegearszerochill.noted.util.mutithreading.BackgroundInsertTask;
import com.fivegearszerochill.noted.util.mutithreading.TaskRunner;
import com.fivegearszerochill.noted.util.repository.ExecutorHelper;
import com.fivegearszerochill.noted.util.repository.PagingHelper;

public class NoteRepository {
    private NoteDao noteDao;
    private TaskRunner taskRunner;
    private OnNoteInsertedCall onNoteInsertedCall;
    private static final String TAG = "NoteRepository";

    public NoteRepository(Application application) {
        NotedDatabase database = NotedDatabase.getInstance(application);
        noteDao = database.getNoteDao();
        taskRunner = new TaskRunner();
    }

    public LiveData<PagedList<NoteAndNotebook>> getRecentNotesPaginatedAsync() {
        DataSource.Factory<Integer, NoteAndNotebook> dataSource = noteDao.getRecentNotesPaginated();

        return new LivePagedListBuilder<>(
                dataSource,
                PagingHelper.getPagingConfig())
                .setFetchExecutor(ExecutorHelper.getSingleThreadExecutor())
                .build();
    }

    public LiveData<PagedList<NoteEntity>> getNotesAsync(long notebookId) {
        DataSource.Factory<Integer, NoteEntity> dataSource = noteDao.getNotesPaginated(notebookId);
        return new LivePagedListBuilder<>(
                dataSource,
                PagingHelper.getPagingConfig())
                .setFetchExecutor(ExecutorHelper.getSingleThreadExecutor())
                .build();
    }

    public void insertNewNoteAsync(NoteEntity note, OnNoteInsertedCall insertedCall) {
        this.onNoteInsertedCall = insertedCall;
        taskRunner.executeAsync(new BackgroundInsertTask(noteDao, note), (data) -> {
            Log.d(TAG, "insertNewNoteAsync: data from callback: " + data);
            if (data != null) {
                onNoteInsertedCall.updateSuccess();
            } else {
                onNoteInsertedCall.updateFailure();
            }
        });
    }
}
