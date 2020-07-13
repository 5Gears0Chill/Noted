package com.fivegearszerochill.noted.room.database;

import com.fivegearszerochill.noted.room.entity.NoteCategoryEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.util.general.DateHelper;

public class PrepopulateHelper {
    public static final boolean ACTIVE = true;
    public static final boolean INACTIVE = false;


    public static NotebookEntity createNotebook(String title, String description, int cardColorId){
        return new NotebookEntity(title, description, cardColorId);
    }

    public static NoteEntity createNote(long notebookId, String title, String content, long categoryId){
        return new NoteEntity(notebookId, title, content, DateHelper.getCurrentDate(),DateHelper.getCurrentDate(),categoryId);
    }

    public static NoteCategoryEntity createCategory(String description) {
        return new NoteCategoryEntity(description, ACTIVE);
    }
}
