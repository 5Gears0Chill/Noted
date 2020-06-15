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
    private long resourceId;

    @ColumnInfo(name = "note_id")
    private long noteId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "type_id")
    private long typeId;

    public NoteResourceEntity(long noteId, String title, String content, long typeId) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getTypeId() {
        return typeId;
    }
}
