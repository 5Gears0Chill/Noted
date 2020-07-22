package com.fivegearszerochill.noted.view.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityNotebookBinding;
import com.fivegearszerochill.noted.databinding.NoteCardBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.adapters.NoteFeed;
import com.fivegearszerochill.noted.view.interfaces.OnNoteClickedListener;
import com.fivegearszerochill.noted.viewmodel.NoteViewModel;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class NotebookActivity extends AppCompatActivity {

    private long notebookId;
    private ActivityNotebookBinding binding;
    private NoteViewModel noteViewModel;
    private NotebookViewModel notebookViewModel;
    private NoteFeed adapter;

    /*FLAGS*/
    private boolean ANIMATION_STATE = false;

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
        handleNoteFeedListeners();
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
            intent.putExtra("notebookId",this.notebookId);
            startActivity(intent);
        });
    }

    private void handleNoteFeedListeners(){
        adapter.setListener(new OnNoteClickedListener() {
            @Override
            public void onEditButtonClicked(View view, int position) {

            }

            @Override
            public void onViewButtonClicked(View view, int position) {

            }

            @Override
            public void onNoteLongPressed(View view, int position, NoteCardBinding binding) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake_minor);
                view.startAnimation(animation);
                ANIMATION_STATE = true;
                OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        view.clearAnimation();
                        binding.ncCloseButton.setVisibility(View.GONE);
                    }
                };
                getOnBackPressedDispatcher().addCallback(callback);
            }

            @Override
            public void onNoteClicked(View view, int position) {

            }

            @Override
            public void onDeleteButtonClicked(View view, int position) {
                view.clearAnimation();
                createDeleteConfirmationPopup(view, position);
            }
        });
    }

    private void createDeleteConfirmationPopup(View view, int position) {
        new MaterialAlertDialogBuilder(view.getRootView().getContext())
                .setTitle("Delete " + adapter.getItemByPosition(position).getTitle() + "?")
                .setMessage("This cannot be undone. " +
                        "Are you sure you would like to delete " +
                        adapter.getItemByPosition(position).getTitle() + "?")
                .setNeutralButton("CANCEL", (dialogInterface, i) ->
                        Toast.makeText(NotebookActivity.this, "CANCELLED", Toast.LENGTH_SHORT)
                                .show())
                .setPositiveButton("DELETE", (dialogInterface, i) -> {
//                    noteViewModel.deleteNote(adapter.getItemByPosition(position));
                    Toast.makeText(NotebookActivity.this, "Deleted " + adapter.getItemByPosition(position).getTitle(), Toast.LENGTH_SHORT)
                            .show();
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       if(!ANIMATION_STATE){
           Intent intent = NavUtils.getParentActivityIntent(this);
           assert intent != null;
           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           NavUtils.navigateUpTo(this, intent);
       }
       ANIMATION_STATE = false;
    }
}