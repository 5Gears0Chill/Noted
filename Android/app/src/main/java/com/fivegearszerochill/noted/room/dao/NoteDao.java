package com.fivegearszerochill.noted.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.queryable.NoteAndAttributes;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithResources;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTagsAndResources;
import com.fivegearszerochill.noted.room.entity.queryable.NoteWithTags;

import java.util.List;

@Dao
public interface NoteDao {

    @Transaction
    @Query("SELECT * FROM note WHERE note_id =:noteId")
    List<NoteAndAttributes> getNoteAndAttributes(int noteId);

    @Transaction
    @Query("SELECT * FROM note WHERE note_id =:noteId")
    List<NoteWithResources> getNoteWithResources(int noteId);

    @Transaction
    @Query("SELECT * FROM note WHERE note_id =:noteId")
    List<NoteWithTags> getNoteWithTags(int noteId);

    @Transaction
    @Query("SELECT * FROM note WHERE note_id =:noteId")
    List<NoteWithTagsAndResources> getNotesWithTagsAndResources(int noteId);

}
