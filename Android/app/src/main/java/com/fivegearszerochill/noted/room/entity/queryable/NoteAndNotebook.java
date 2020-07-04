package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;

public class NoteAndNotebook {
    @Embedded
    private NoteEntity note;


    @Relation(
            parentColumn = "note_id",
            entityColumn = "notebook_id"
    )
    private NotebookEntity notebook;

    public NoteAndNotebook(NoteEntity note, NotebookEntity notebook) {
        this.note = note;
        this.notebook = notebook;
    }


    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public NotebookEntity getNotebook() {
        return notebook;
    }

    public void setNotebook(NotebookEntity notebook) {
        this.notebook = notebook;
    }


    public static final DiffUtil.ItemCallback<NoteAndNotebook> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NoteAndNotebook>() {

        @Override
        public boolean areItemsTheSame(@NonNull NoteAndNotebook oldItem, @NonNull NoteAndNotebook newItem) {
            return oldItem.getNote().getNoteId() == newItem.getNote().getNoteId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoteAndNotebook oldItem, @NonNull NoteAndNotebook newItem) {
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
            NoteAndNotebook note = (NoteAndNotebook) obj;
            return note.getNote().getNoteId() == this.getNote().getNoteId();
        }
        return false;
    }
}
