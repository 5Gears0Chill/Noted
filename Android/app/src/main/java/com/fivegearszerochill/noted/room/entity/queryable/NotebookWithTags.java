package com.fivegearszerochill.noted.room.entity.queryable;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.NotebookTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;

import java.util.List;

public class NotebookWithTags {

    @Embedded
    private NotebookEntity notebook;

    @Relation(parentColumn = "notebook_id",
            entityColumn = "tag_id",
            associateBy = @Junction(NotebookTagCrossReferenceEntity.class))
    private List<TagEntity> tags;
}
