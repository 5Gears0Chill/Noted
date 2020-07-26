package com.fivegearszerochill.noted.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityNotebookBinding;
import com.fivegearszerochill.noted.view.fragments.NotesFragment;
import com.fivegearszerochill.noted.view.fragments.UnsplashFragment;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;

public class NotebookActivity extends AppCompatActivity {

    private long notebookId;

    private ActivityNotebookBinding binding;
    private NotebookViewModel notebookViewModel;
    /*FLAGS*/
    private boolean ANIMATION_STATE = false;
    private static final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT_TAG";
    private static final String UNSPLASH_FRAGMENT_TAG = "UNSPLASH_FRAGMENT_TAG";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.customise_menu_item).setEnabled(this.ANIMATION_STATE);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.customise_menu_item) {
            handleBottomSheetDrawable();
            Toast.makeText(this, "Clicked Edit", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        binding = ActivityNotebookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtras();
        initViewModels();
        manageFragmentTransactions();
    }

    private void initViewModels() {
        notebookViewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(NotebookViewModel.class);
    }


    private void getIntentExtras() {
        Intent intent = getIntent();
        this.notebookId = intent.getLongExtra("notebookId", 0);
    }

    private void handleInitialNoteLoading() {
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
            intent.putExtra("notebookId", this.notebookId);
            startActivity(intent);
        });
    }

    public void setAnimationState(boolean ANIMATION_STATE) {
        this.ANIMATION_STATE = ANIMATION_STATE;
    }

    private void manageFragmentTransactions() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_placeholder, NotesFragment.newInstance(this.notebookId), NOTES_FRAGMENT_TAG)
                .commit();

    }

    private void handleBottomSheetDrawable() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(NOTES_FRAGMENT_TAG);
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
                    .add(R.id.fragment_placeholder, UnsplashFragment.newInstance("", ""), UNSPLASH_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!ANIMATION_STATE) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            assert intent != null;
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NavUtils.navigateUpTo(this, intent);
        }
        ANIMATION_STATE = false;
    }
}