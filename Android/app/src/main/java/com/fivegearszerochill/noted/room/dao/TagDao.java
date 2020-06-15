package com.fivegearszerochill.noted.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;
import com.fivegearszerochill.noted.room.entity.queryable.TagWithNotes;

import java.util.List;

@Dao
public abstract class TagDao {

    @Transaction
    @Query("SELECT * FROM tags WHERE tag_id =:tagId")
    abstract DataSource.Factory<Integer,TagWithNotes> getPaginatedNotesWithTag(int tagId);

    @Transaction
    @Query("SELECT * FROM tags ORDER BY description")
    abstract List<TagEntity> getAllTags();

    @Insert
    abstract long createNewTag(TagEntity tag);

    @Insert
    abstract void createCrossRef(NoteTagCrossReferenceEntity crossReferenceEntity);

    @Transaction
    public void addNonExistentTagFromNote(int noteId, TagEntity tag){
        long tagId = createNewTag(tag);
        createCrossRef(new NoteTagCrossReferenceEntity(noteId, tagId));
    }
}
