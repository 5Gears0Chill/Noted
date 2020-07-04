package com.fivegearszerochill.noted.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityNotebookBinding;

public class NotebookActivity extends AppCompatActivity {
    private int notebookId;
    private ActivityNotebookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        binding = ActivityNotebookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtras();
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        this.notebookId = intent.getIntExtra("notebookId", 0);
    }
}