package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notebook")
public class NotebookEntity implements CoreEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notebook_id")
    private long notebookId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "tag_id")
    private long tagId;

    public NotebookEntity(String title, String description, long tagId) {
        this.title = title;
        this.description = description;
        this.tagId = tagId;
    }

    public void setNotebookId(long notebookId) {
        this.notebookId = notebookId;
    }

    public long getNotebookId() {
        return notebookId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getTagId() {
        return tagId;
    }
}
