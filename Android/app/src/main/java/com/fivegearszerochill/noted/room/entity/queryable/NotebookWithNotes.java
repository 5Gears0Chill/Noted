package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;

import java.util.List;

public class NotebookWithNotes {
    @Embedded
    private NotebookEntity notebook;

    @Relation(
            parentColumn = "notebook_id",
            entityColumn = "notebook_id"
    )
    private List<NoteEntity> notes;

    public NotebookWithNotes(NotebookEntity notebook, List<NoteEntity> notes) {
        this.notebook = notebook;
        this.notes = notes;
    }

    public NotebookEntity getNotebook() {
        return notebook;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }
}
