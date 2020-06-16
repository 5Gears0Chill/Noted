package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;

import java.util.List;

public class NoteWithTags {
    @Embedded
    private NoteEntity note;

    @Relation(parentColumn = "note_id",
            entityColumn = "tag_id",
            associateBy = @Junction(NoteTagCrossReferenceEntity.class))
    private List<TagEntity> tags;

    public NoteWithTags(NoteEntity note, List<TagEntity> tags) {
        this.note = note;
        this.tags = tags;
    }

    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}
