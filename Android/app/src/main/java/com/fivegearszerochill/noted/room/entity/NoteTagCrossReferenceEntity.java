package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "note_tag_cross_ref", primaryKeys = {"tag_id", "note_id"})
public class NoteTagCrossReferenceEntity {

    @ColumnInfo(name = "tag_id")
    private long tagId;

    @ColumnInfo(name = "note_id")
    private long noteId;


    public NoteTagCrossReferenceEntity(long tagId, long noteId) {
        this.tagId = tagId;
        this.noteId = noteId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }


    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getTagId() {
        return tagId;
    }

    public long getNoteId() {
        return noteId;
    }
}
