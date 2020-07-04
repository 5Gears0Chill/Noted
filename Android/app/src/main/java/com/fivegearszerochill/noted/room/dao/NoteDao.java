package com.fivegearszerochill.noted.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.fivegearszerochill.noted.room.entity.CoreEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithResources;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTags;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTagsAndResources;

@Dao
public abstract class NoteDao implements CoreDao {

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteAndNotebook getNoteAndAttributes(int noteId);

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "ORDER BY note.created_on ASC"
    )
    public abstract DataSource.Factory<Integer, NoteAndNotebook> getRecentNotesPaginated();

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteWithResources getNoteWithResources(int noteId);

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteWithTags getNoteWithTags(int noteId);

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteWithTagsAndResources getNoteWithTagsAndResources(int noteId);

    @Insert
    public abstract long createNote(NoteEntity note);


    @Override
    public long insertAsync(CoreEntity entity) {
        return createNote((NoteEntity) entity);
    }

    @Update
    abstract void updateNote(NoteEntity entity);

    @Transaction
    @Query("DELETE " +
            "FROM note " +
            "WHERE note_id =:noteId")
    abstract void deleteNote(int noteId);

    @Override
    public void deleteAsync(CoreEntity entity) {

    }

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note.notebook_id = :notebookId " +
                    "ORDER BY note.created_on"
    )
    public abstract DataSource.Factory<Integer, NoteEntity> getNotesPaginated(long notebookId);
}
