package com.fivegearszerochill.noted.room.database;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fivegearszerochill.noted.room.dao.NoteDao;
import com.fivegearszerochill.noted.room.dao.NotebookDao;
import com.fivegearszerochill.noted.room.dao.TagDao;
import com.fivegearszerochill.noted.room.entity.NoteAttributeEntity;
import com.fivegearszerochill.noted.room.entity.NoteCategoryEntity;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.room.entity.NoteResourceEntity;
import com.fivegearszerochill.noted.room.entity.NoteTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.room.entity.NotebookTagCrossReferenceEntity;
import com.fivegearszerochill.noted.room.entity.ResourceTypeEntity;
import com.fivegearszerochill.noted.room.entity.TagEntity;
import com.fivegearszerochill.noted.util.mutithreading.BackgroundTask;
import com.fivegearszerochill.noted.util.mutithreading.TaskRunner;

@Database(entities = {
        NoteAttributeEntity.class,
        NotebookEntity.class,
        NoteCategoryEntity.class,
        NoteEntity.class,
        NoteResourceEntity.class,
        NoteTagCrossReferenceEntity.class,
        NotebookTagCrossReferenceEntity.class,
        ResourceTypeEntity.class,
        TagEntity.class
}, version = NotedDatabase.VERSION)
@TypeConverters({Converters.class})
public abstract class NotedDatabase extends RoomDatabase {
    public static final int VERSION = 1;

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


    private static RoomDatabase.Callback prepopulateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsync(instance).execute();
        }
    };

    public static class PopulateAsync extends AsyncTask<Void,Void,Void>{
        private NotebookDao notebookDao;

        private PopulateAsync(NotedDatabase db){
            this.notebookDao = db.getNoteBookDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test 1", "Testing Description 1", 0));
            notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test 2", "Testing Description 2", 0));
            notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test 3", "Testing Description 3", 0));
            notebookDao.addNewNoteBook(PrepopulateHelper.createNotebook("Test 4", "Testing Description 4", 0));
            return null;
        }
    }
}
