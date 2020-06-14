package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "note",
        foreignKeys = @ForeignKey(entity = NotebookEntity.class,
                parentColumns = "notebook_id",
                childColumns = "notebook_id"))
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int noteId;

    @ColumnInfo(name = "notebook_id")
    private int notebookId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public NoteEntity(int notebookId, String title, String content, boolean isActive) {
        this.notebookId = notebookId;
        this.title = title;
        this.content = content;
        this.isActive = isActive;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isActive() {
        return isActive;
    }
}
