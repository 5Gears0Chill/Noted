package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "note",
        foreignKeys = @ForeignKey(entity = NotebookEntity.class,
                parentColumns = "notebook_id",
                childColumns = "notebook_id"),
        indices = {@Index(value = "notebook_id")})
public class NoteEntity implements CoreEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private long noteId;

    @ColumnInfo(name = "notebook_id")
    private long notebookId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    public NoteEntity(long notebookId, String title, String content) {
        this.notebookId = notebookId;
        this.title = title;
        this.content = content;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getNoteId() {
        return noteId;
    }

    public long getNotebookId() {
        return notebookId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
