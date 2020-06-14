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
public class NoteAttributeEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_attribute_id")
    private int noteAttributeId;

    @ColumnInfo(name = "note_id")
    private int noteId;

    @ColumnInfo(name = "created_on")
    private Date createdOn;

    @ColumnInfo(name = "updated_on")
    private Date updatedOn;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "file_size")
    private int fileSize;

    public NoteAttributeEntity(int noteId, Date createdOn, Date updatedOn, int categoryId, int fileSize) {
        this.noteId = noteId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.categoryId = categoryId;
        this.fileSize = fileSize;
    }

    public void setNoteAttributeId(int noteAttributeId) {
        this.noteAttributeId = noteAttributeId;
    }

    public int getNoteAttributeId() {
        return noteAttributeId;
    }

    public int getNoteId() {
        return noteId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getFileSize() {
        return fileSize;
    }
}
