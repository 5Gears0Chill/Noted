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
    public static final int VERSION = 6;

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
        }
    };
}
