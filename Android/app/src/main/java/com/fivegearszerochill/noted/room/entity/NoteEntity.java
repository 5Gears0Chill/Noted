package com.fivegearszerochill.noted.room.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;

import java.util.Date;

@Entity(tableName = "note",
        foreignKeys = @ForeignKey(
                entity = NotebookEntity.class,
                parentColumns = "notebook_id",
                childColumns = "notebook_id",
                onDelete = ForeignKey.CASCADE),
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

    @ColumnInfo(name = "created_on")
    private Date createdOn;

    @ColumnInfo(name = "updated_on")
    private Date updatedOn;


    public NoteEntity(long notebookId, String title, String content, Date createdOn, Date updatedOn) {
        this.notebookId = notebookId;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public static final DiffUtil.ItemCallback<NoteEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NoteEntity>() {

                @Override
                public boolean areItemsTheSame(@NonNull NoteEntity oldItem, @NonNull NoteEntity newItem) {
                    return oldItem.getNoteId() == newItem.getNoteId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull NoteEntity oldItem, @NonNull NoteEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj != null) {
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            NoteEntity note = (NoteEntity) obj;
            return note.getNoteId() == this.getNoteId();
        }
        return false;
    }
}
