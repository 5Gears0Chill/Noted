package com.fivegearszerochill.noted.room.database;

import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteCategoryEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.util.room.NoteAttributeHelper;

import java.util.List;

public class PrepopulateHelper {
    public static final boolean ACTIVE = true;
    public static final boolean INACTIVE = false;


    public static NotebookEntity createNotebook(String title, String description, long tagId){
        return new NotebookEntity(title, description, tagId);
    }

    public static NoteEntity createNote(long notebookId, String title, String content){
        return new NoteEntity(notebookId, title, content);
    }

    public static NoteAttributeEntity createNoteAttribute(long noteId, long categoryId){
        return new NoteAttributeEntity(
                noteId,
                NoteAttributeHelper.getCurrentDate(),
                NoteAttributeHelper.getCurrentDate(),
                categoryId
        );
    }

    public static NoteCategoryEntity createCategory(String description) {
        return new NoteCategoryEntity(description, ACTIVE);
    }
}
