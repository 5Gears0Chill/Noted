package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notebook")
public class NotebookEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notebook_id")
    private int notebookId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "tag_id")
    private int tagId;

    public NotebookEntity(String title, String description, int tagId) {
        this.title = title;
        this.description = description;
        this.tagId = tagId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getTagId() {
        return tagId;
    }
}
