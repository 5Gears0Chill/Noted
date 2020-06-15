package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tags")
public class TagEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    private long tagId;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public TagEntity(String description, boolean isActive) {
        this.description = description;
        this.isActive = isActive;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getTagId() {
        return tagId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }
}
