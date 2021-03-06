package com.fivegearszerochill.noted.room.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.CoreEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NotebookWithNotes;

@Dao
public abstract class NotebookDao implements CoreDao {

    @Transaction
    @Query("SELECT * " +
            "FROM notebook WHERE notebook_id =:notebookId")
    public abstract NotebookWithNotes getNotebookWithNotes(int notebookId);

    @Transaction
    @Query("SELECT * " +
            "FROM notebook ORDER BY title")
    public abstract DataSource.Factory<Integer, NotebookEntity> getPagedNotebooks();

    @Insert
    public abstract long addNewNoteBook(NotebookEntity notebook);

    @Override
    public long insertAsync(CoreEntity entity) {
        return addNewNoteBook((NotebookEntity) entity);
    }

    @Delete
    public abstract void deleteNotebook(NotebookEntity entity);

    @Override
    public void deleteAsync(CoreEntity entity) {
        deleteNotebook((NotebookEntity) entity);
    }

    @Transaction
    @Query("SELECT * " +
            "FROM notebook WHERE notebook_id =:notebookId")
    public abstract LiveData<NotebookEntity> getNotebook(long notebookId);
}
