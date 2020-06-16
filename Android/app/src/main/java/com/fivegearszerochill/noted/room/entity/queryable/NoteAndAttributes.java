package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;

public class NoteAndAttributes {
    @Embedded
    private NoteEntity note;

    @Relation(
            parentColumn = "note_id",
            entityColumn = "note_attribute_id"
    )
    private NoteAttributeEntity attributes;

    public NoteAndAttributes(NoteEntity note, NoteAttributeEntity attributes) {
        this.note = note;
        this.attributes = attributes;
    }

    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public NoteAttributeEntity getAttributes() {
        return attributes;
    }

    public void setAttributes(NoteAttributeEntity attributes) {
        this.attributes = attributes;
    }
}
