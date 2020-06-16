package com.fivegearszerochill.noted.room.database;

import com.fivegearszerochill.noted.room.entity.NotebookEntity;

import java.util.List;

public class PrepopulateHelper {

    public static NotebookEntity createNotebook(String title, String description, long tagId){
        return new NotebookEntity(title, description, tagId);
    }
}
