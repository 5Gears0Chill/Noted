package com.fivegearszerochill.noted.util.app;

import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.util.general.DateHelper;
import com.google.gson.Gson;

public class ModelHelper {
    public static NoteEntity constructNote(DraftModel draft) {
        return new NoteEntity(
                draft.getNotebookId(),
                draft.getDraftTitle(),
                new Gson().toJson(draft),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate()
        );
    }
}
