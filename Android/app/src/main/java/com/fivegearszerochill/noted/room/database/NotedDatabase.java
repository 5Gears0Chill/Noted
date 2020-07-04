package com.fivegearszerochill.noted.room.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fivegearszerochill.noted.room.dao.CategoryDao;
import com.fivegearszerochill.noted.room.dao.NoteDao;
import com.fivegearszerochill.noted.room.dao.NotebookDao;
import com.fivegearszerochill.noted.room.dao.TagDao;
import com.fivegearszerochill.noted.room.entity.NoteCategoryEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteResourceEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.ResourceTypeEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;

@Database(entities = {
        NotebookEntity.class,
        NoteCategoryEntity.class,
        NoteEntity.class,
        NoteResourceEntity.class,
        NoteTagCrossReferenceEntity.class,
        ResourceTypeEntity.class,
        TagEntity.class
}, version = NotedDatabase.VERSION)
@TypeConverters({Converters.class})
public abstract class NotedDatabase extends RoomDatabase {
    public static final int VERSION = 4;

    private static NotedDatabase instance;

    public static synchronized NotedDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NotedDatabase.class,
                    "noted_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(prepopulateCallback)
                    .build();
        }
        return instance;
    }

    public abstract NoteDao getNoteDao();

    public abstract NotebookDao getNoteBookDao();

    public abstract TagDao getTagDao();

    public abstract CategoryDao getCategoryDao();


    private static RoomDatabase.Callback prepopulateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsync(instance).execute();
        }
    };

    public static class PopulateAsync extends AsyncTask<Void, Void, Void> {
        private NotebookDao notebookDao;
        private NoteDao noteDao;
        private CategoryDao categoryDao;

        private PopulateAsync(NotedDatabase db) {
            this.notebookDao = db.getNoteBookDao();
            this.noteDao = db.getNoteDao();
            this.categoryDao = db.getCategoryDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
//            long categoryId1 = categoryDao.createCategory(PrepopulateHelper.createCategory("Diary"));
//            long categoryId2 = categoryDao.createCategory(PrepopulateHelper.createCategory("Movie Reviews"));
//            long categoryId3 = categoryDao.createCategory(PrepopulateHelper.createCategory("Ideas"));
//            long categoryId4 = categoryDao.createCategory(PrepopulateHelper.createCategory("Work Stuff"));
//
//            long noteBookId1 = notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test Title 1", "Testing Description 1", 0,));
//            long noteBookId2 = notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test Title 2", "Testing Description 2", 0));
//            long noteBookId3 = notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test Title 3", "Testing Description 3", 0));
//            long noteBookId4 = notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test Title 4", "Testing Description 4", 0));
//
//            long noteId1 = noteDao.createNote(PrepopulateHelper.createNote(noteBookId1, "Note Title 1", "Hello World I am Note 1"));
//            long noteId2 = noteDao.createNote(PrepopulateHelper.createNote(noteBookId2, "Note Title 2", "Hello World I am Note 1"));
//            long noteId3 = noteDao.createNote(PrepopulateHelper.createNote(noteBookId3, "Note Title 3", "Hello World I am Note 1"));
//            long noteId4 = noteDao.createNote(PrepopulateHelper.createNote(noteBookId4, "Note Title 4", "Hello World I am Note 1"));
//
//            noteDao.createNoteAttribute(PrepopulateHelper.createNoteAttribute(noteId1,categoryId1));
//            noteDao.createNoteAttribute(PrepopulateHelper.createNoteAttribute(noteId2,categoryId2));
//            noteDao.createNoteAttribute(PrepopulateHelper.createNoteAttribute(noteId3,categoryId3));
//            noteDao.createNoteAttribute(PrepopulateHelper.createNoteAttribute(noteId4,categoryId4));
            return null;
        }
    }
}
