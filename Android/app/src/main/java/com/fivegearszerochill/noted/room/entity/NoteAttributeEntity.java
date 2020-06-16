package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;

@Entity(tableName = "note_attribute",
        foreignKeys = @ForeignKey(entity = NoteCategoryEntity.class,
                parentColumns = "category_id",
                childColumns = "category_id",
                onDelete = ForeignKey.CASCADE))
public class NoteAttributeEntity implements CoreEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_attribute_id")
    private long noteAttributeId;

    @ColumnInfo(name = "note_id")
    private long noteId;

    @ColumnInfo(name = "created_on")
    private Date createdOn;

    @ColumnInfo(name = "updated_on")
    private Date updatedOn;

    @ColumnInfo(name = "category_id")
    private long categoryId;


    public NoteAttributeEntity(long noteId, Date createdOn, Date updatedOn, long categoryId) {
        this.noteId = noteId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.categoryId = categoryId;
    }

    public void setNoteAttributeId(int noteAttributeId) {
        this.noteAttributeId = noteAttributeId;
    }

    public long getNoteAttributeId() {
        return noteAttributeId;
    }

    public long getNoteId() {
        return noteId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
