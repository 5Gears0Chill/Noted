package com.fivegearszerochill.noted.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NotebookWithNotes;

import java.util.List;

@Dao
public abstract class NotebookDao {

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

}
