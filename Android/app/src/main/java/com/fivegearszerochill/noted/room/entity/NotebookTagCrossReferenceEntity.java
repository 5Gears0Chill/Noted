package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "notebook_tag_cross_ref", primaryKeys = {"tag_id", "notebook_id"})
public class NotebookTagCrossReferenceEntity {

    @ColumnInfo(name = "tag_id")
    private long tagId;

    @ColumnInfo(name = "notebook_id")
    private long noteId;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
