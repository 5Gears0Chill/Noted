package com.fivegearszerochill.noted.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fivegearszerochill.noted.databinding.ActivityNotebookBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.adapters.NoteFeed;
import com.fivegearszerochill.noted.viewmodel.NoteViewModel;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;

public class NotebookActivity extends AppCompatActivity {

    private long notebookId;
    private ActivityNotebookBinding binding;
    private NoteViewModel noteViewModel;
    private NotebookViewModel notebookViewModel;
    private NoteFeed adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleInitialNoteLoading();
        handleFabInit();
    }

    private void init() {
        binding = ActivityNotebookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtras();
        initViewModels();
        initAdapters();
    }

    private void initViewModels() {
        noteViewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(NoteViewModel.class);

        notebookViewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(NotebookViewModel.class);
    }

    private void initAdapters() {
        adapter = new NoteFeed(getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        binding.nRecyclerView.setLayoutManager(manager);
        binding.nRecyclerView.setAdapter(adapter);
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        this.notebookId = intent.getLongExtra("notebookId", 0);
    }

    private void handleInitialNoteLoading() {
        noteViewModel.getPaginatedNotes(this.notebookId)
                .observe(this, noteEntities -> {
                    adapter.submitList(noteEntities);
                });

        notebookViewModel.getNotebook(notebookId)
                .observe(this, entity -> {
                    binding.nTitle.setText(entity.getTitle());
                    binding.nDescription.setText(entity.getDescription());
                    binding.nCardIcoColor.setCardBackgroundColor(entity.getCardColorId());
                });
    }

    private void handleFabInit() {
        binding.nFab.setOnClickListener(view -> {
            Intent intent = new Intent(NotebookActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        });
    }
}