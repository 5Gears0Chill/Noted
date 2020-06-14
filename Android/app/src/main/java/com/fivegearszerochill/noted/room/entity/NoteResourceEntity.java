package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_resource",
        foreignKeys = {
                @ForeignKey(entity = ResourceTypeEntity.class,
                        parentColumns = "type_id",
                        childColumns = "type_id"),
                @ForeignKey(entity = NoteEntity.class,
                        parentColumns = "note_id",
                        childColumns = "note_id")
        })
public class NoteResourceEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resource_id")
    private int resourceId;

    @ColumnInfo(name = "note_id")
    private int noteId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "type_id")
    private int typeId;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public NoteResourceEntity(int noteId, String title, String content, int typeId, boolean isActive) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
        this.isActive = isActive;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getTypeId() {
        return typeId;
    }

    public boolean isActive() {
        return isActive;
    }
}
