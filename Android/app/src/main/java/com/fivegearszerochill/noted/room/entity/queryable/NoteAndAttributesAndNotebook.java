package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;

public class NoteAndAttributesAndNotebook {
    @Embedded
    private NoteEntity note;


    @Relation(
            parentColumn = "note_id",
            entityColumn = "note_attribute_id"
    )
    private NoteAttributeEntity attributes;


    @Relation(
            parentColumn = "note_id",
            entityColumn = "notebook_id"
    )
    private NotebookEntity notebook;

    public NoteAndAttributesAndNotebook(NoteEntity note, NoteAttributeEntity attributes, NotebookEntity notebook) {
        this.note = note;
        this.attributes = attributes;
        this.notebook = notebook;
    }


    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public NoteAttributeEntity getAttributes() {
        return attributes;
    }

    public void setAttributes(NoteAttributeEntity attributes) {
        this.attributes = attributes;
    }

    public NotebookEntity getNotebook() {
        return notebook;
    }

    public void setNotebook(NotebookEntity notebook) {
        this.notebook = notebook;
    }


    public static final DiffUtil.ItemCallback<NoteAndAttributesAndNotebook> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NoteAndAttributesAndNotebook>() {

        @Override
        public boolean areItemsTheSame(@NonNull NoteAndAttributesAndNotebook oldItem, @NonNull NoteAndAttributesAndNotebook newItem) {
            return oldItem.getNote().getNoteId() == newItem.getNote().getNoteId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoteAndAttributesAndNotebook oldItem, @NonNull NoteAndAttributesAndNotebook newItem) {
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
            NoteAndAttributesAndNotebook note = (NoteAndAttributesAndNotebook) obj;
            return note.getNote().getNoteId() == this.getNote().getNoteId();
        }
        return false;
    }
}
