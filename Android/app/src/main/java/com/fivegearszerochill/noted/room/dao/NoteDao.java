package com.fivegearszerochill.noted.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndAttributes;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithResources;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTags;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTagsAndResources;
import com.fivegearszerochill.noted.util.room.NoteAttributeHelper;

@Dao
public abstract class NoteDao {

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteAndAttributes getNoteAndAttributes(int noteId);

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
    abstract NoteWithTagsAndResources getNotesWithTagsAndResources(int noteId);

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "INNER JOIN note_attribute " +
                    "ON note.note_id = note_attribute.note_id " +
                    "ORDER BY created_on"
    )
    abstract DataSource.Factory<Integer, NoteAndAttributes> getPaginatedNotesAndAttributes();

    @Insert
    abstract long createNote(NoteEntity note);

    @Insert
    abstract long createNoteAttribute(NoteAttributeEntity noteAttribute);

    @Transaction
    public void createGenericNote(NoteEntity note) {
        long noteId = createNote(note);

        NoteAttributeEntity attribute = new NoteAttributeEntity(
                noteId,
                NoteAttributeHelper.getCurrentDate(),
                NoteAttributeHelper.getCurrentDate(),
                0
        );

        createNoteAttribute(attribute);
    }

    @Update
    abstract void updateNote(NoteEntity entity);

    @Update
    abstract void updateNoteAttribute(NoteAttributeEntity noteAttribute);

    @Transaction
    public void updateGenericNote(NoteEntity note, NoteAttributeEntity noteAttribute) {
        updateNote(note);
        updateNoteAttribute(noteAttribute);
    }

    @Transaction
    @Query("DELETE " +
            "FROM note " +
            "WHERE note_id =:noteId")
    abstract void deleteNote(int noteId);
}
