package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteResourceEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;

import java.util.List;

public class NoteWithTagsAndResources {
    @Embedded
    private NoteEntity note;

    @Relation(
            parentColumn = "note_id",
            entityColumn = "note_id"
    )
    private List<NoteResourceEntity> resources;

    @Relation(parentColumn = "note_id",
            entityColumn = "tag_id",
            associateBy = @Junction(NoteTagCrossReferenceEntity.class))
    private List<TagEntity> tags;

    public NoteWithTagsAndResources(NoteEntity note, List<NoteResourceEntity> resources, List<TagEntity> tags) {
        this.note = note;
        this.resources = resources;
        this.tags = tags;
    }

    public NoteEntity getNote() {
        return note;
    }

    public List<NoteResourceEntity> getResources() {
        return resources;
    }

    public List<TagEntity> getTags() {
        return tags;
    }
}
