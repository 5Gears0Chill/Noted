package com.fivegearszerochill.noted.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.queryable.NotebookWithNotes;

import java.util.List;

@Dao
public interface NotebookDao {

    @Transaction
    @Query("SELECT * FROM notebook WHERE notebook_id =:notebookId")
    List<NotebookWithNotes> getNotebookWithNotes(int notebookId);


}
