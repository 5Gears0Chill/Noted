package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "note_tag_cross_ref", primaryKeys = {"tag_id", "note_id"})
public class NoteTagCrossReference {

    @ColumnInfo(name = "tag_id")
    private int tagId;

    @ColumnInfo(name = "note_id")
    private int noteId;

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getTagId() {
        return tagId;
    }

    public int getNoteId() {
        return noteId;
    }
}
