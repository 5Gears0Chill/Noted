package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "notebook_tag_cross_ref", primaryKeys = {"tag_id", "notebook_id"})
public class NotebookTagCrossReferenceEntity {

    @ColumnInfo(name = "tag_id")
    private int tagId;

    @ColumnInfo(name = "notebook_id")
    private int noteId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
