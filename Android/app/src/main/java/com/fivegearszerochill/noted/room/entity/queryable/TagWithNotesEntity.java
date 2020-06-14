package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReference;
import com.fivegearszerochill.noted.room.entity.TagEntity;

import java.util.List;

public class TagWithNotesEntity {
    @Embedded
    private TagEntity tag;

    @Relation(parentColumn = "tag_id",
            entityColumn = "note_id",
            associateBy = @Junction(NoteTagCrossReference.class))
    private List<NoteEntity> notes;

    public TagWithNotesEntity(TagEntity tag, List<NoteEntity> notes) {
        this.tag = tag;
        this.notes = notes;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
}
