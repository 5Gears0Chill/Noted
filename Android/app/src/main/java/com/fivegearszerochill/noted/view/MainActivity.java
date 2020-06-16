package com.fivegearszerochill.noted.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import android.os.Bundle;
import android.util.Log;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.repository.NotebookRepository;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;

public class MainActivity extends AppCompatActivity {

    NotebookRepository repository;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));
        setContentView(R.layout.activity_main);

        repository = new NotebookRepository(getApplication());
    }

    @Override
    protected void onStart() {
        super.onStart();

        repository.getPaginatedNotesAsync().observe(this, notebookEntities -> {
            for (NotebookEntity notebook:
                 notebookEntities) {
                Log.d(TAG,notebook.getTitle() + ' ' + notebook.getDescription() + ' ' + notebook.getTagId());
            }
        });
    }
}