package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteResourceEntity;

import java.util.List;

public class NoteWithResources {
    @Embedded
    private NoteEntity note;

    @Relation(
            parentColumn = "note_id",
            entityColumn = "note_id"
    )
    private List<NoteResourceEntity> resources;

    public NoteWithResources(NoteEntity note, List<NoteResourceEntity> resources) {
        this.note = note;
        this.resources = resources;
    }

    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public List<NoteResourceEntity> getResources() {
        return resources;
    }

    public void setResources(List<NoteResourceEntity> resources) {
        this.resources = resources;
    }
}
