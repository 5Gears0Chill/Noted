package com.fivegearszerochill.noted.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.fivegearszerochill.noted.room.entity.CoreEntity;
import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndAttributesAndNotebook;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithResources;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTags;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTagsAndResources;
import com.fivegearszerochill.noted.util.room.NoteAttributeHelper;

@Dao
public abstract class NoteDao implements CoreDao {

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "WHERE note_id =:noteId"
    )
    abstract NoteAndAttributesAndNotebook getNoteAndAttributes(int noteId);

    @Transaction
    @Query(
            "SELECT * FROM note " +
                    "INNER JOIN note_attribute " +
                    "ON note_attribute.note_id = note.note_id " +
                    "ORDER BY note_attribute.created_on DESC"
    )
    public abstract DataSource.Factory<Integer,NoteAndAttributesAndNotebook> getRecentNotesPaginated();

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

//    @Transaction
//    @Query(
//            "SELECT note.note_id, notebook_id, title,content, note_attribute.created_on,updated_on, category_id  " +
//                    "FROM note " +
//                    "INNER JOIN note_attribute " +
//                    "ON note.note_id = note_attribute.note_id " +
//                    "ORDER BY created_on"
//    )
//    public abstract DataSource.Factory<Integer, NoteAndAttributes> getPaginatedNotesAndAttributes();

    @Insert
    public abstract long createNote(NoteEntity note);

    @Insert
    public abstract void createNoteAttribute(NoteAttributeEntity noteAttribute);

    @Transaction
    @Override
    public long insertAsync(CoreEntity entity) {
        long noteId = createNote((NoteEntity) entity);

        NoteAttributeEntity attribute = new NoteAttributeEntity(
                noteId,
                NoteAttributeHelper.getCurrentDate(),
                NoteAttributeHelper.getCurrentDate(),
                0
        );

        createNoteAttribute(attribute);

        return noteId;
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
