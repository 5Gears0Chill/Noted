package com.fivegearszerochill.noted.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.repository.NotebookRepository;
import com.fivegearszerochill.noted.room.database.PrepopulateHelper;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    Button button;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        button = findViewById(R.id.button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.getPaginatedNotes().observe(this, notebookEntities -> {
            for (NotebookEntity notebook:
                 notebookEntities) {
                Log.d(TAG,notebook.getTitle() + ' ' + notebook.getDescription() + ' ' + notebook.getTagId());
            }
        });

        button.setOnClickListener(view -> {
            Log.d(TAG,"Inside Button on click listener = button clicked");
            viewModel.insertNotebook(PrepopulateHelper.createNotebook("Damn","DAMN BRO", 0));
        });
    }
}