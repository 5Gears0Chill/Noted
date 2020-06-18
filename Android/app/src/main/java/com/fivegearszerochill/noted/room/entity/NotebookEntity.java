package com.fivegearszerochill.noted.room.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj != null) {
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            NotebookEntity notebook = (NotebookEntity) obj;
            return notebook.getNotebookId() == this.getNotebookId();
        }
       return false;
    }


    public static final DiffUtil.ItemCallback<NotebookEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<NotebookEntity>() {

        @Override
        public boolean areItemsTheSame(@NonNull NotebookEntity oldItem, @NonNull NotebookEntity newItem) {
            return oldItem.getNotebookId() == newItem.getNotebookId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotebookEntity oldItem, @NonNull NotebookEntity newItem) {
            return oldItem.equals(newItem);
        }
    };
}
