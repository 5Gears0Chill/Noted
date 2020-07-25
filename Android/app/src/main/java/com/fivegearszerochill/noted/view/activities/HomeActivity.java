package com.fivegearszerochill.noted.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityHomeBinding;
import com.fivegearszerochill.noted.databinding.NotebookCardBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.unsplash.models.Photo;
import com.fivegearszerochill.noted.unsplash.requests.UnsplashSearchRequest;
import com.fivegearszerochill.noted.view.adapters.NotebookFeed;
import com.fivegearszerochill.noted.view.adapters.SectionsPagerAdapter;
import com.fivegearszerochill.noted.view.interfaces.OnNotebookItemClickListener;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.UnsplashViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private NotebookViewModel viewModel;
    private ActivityHomeBinding binding;
    private NotebookFeed adapter;


    //Testing
    private UnsplashViewModel unsplashViewModel;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleInitialNotebookLoading();
        handleFabOnClickEvent();
        handleNotebookCardListeners();

//        unsplashViewModel = new ViewModelProvider(this).get(UnsplashViewModel.class);
//        unsplashViewModel.init();
//        unsplashViewModel.getPhotos().observe(this, photos -> {
//            if(photos!=null){
//                for (Photo p :
//                        photos) {
//                    Log.d(TAG, p.toString());
//                }
//            }
//        });

        UnsplashSearchRequest request = new UnsplashSearchRequest.Builder("socks")
                .setColor("black_and_white")
                .setContentFilter("low")
                .setOrderBy("relevant")
                .setOrientation("landscape")
                .setPage(1)
                .setPerPage(20)
                .build();
        Map<String, Object> map = request.toMap();
        Log.d(TAG, String.valueOf(map));
        unsplashViewModel = new ViewModelProvider(this).get(UnsplashViewModel.class);
        unsplashViewModel.init();
        unsplashViewModel.getPhotos(request.toMap()).observe(this, photos -> {
            if (photos != null) {
                for (Photo p :
                        photos.getResults()) {
                    Log.d(TAG, p.toString());
                }
            }
        });
    }

    private void init() {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.toolbarLayout.setTitle(getTitle());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        binding.contentScrolling.viewPager.setAdapter(sectionsPagerAdapter);
        binding.contentScrolling.tabs.setupWithViewPager(binding.contentScrolling.viewPager);

        viewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(NotebookViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        binding.contentScrolling.recyclerView.setLayoutManager(manager);
        adapter = new NotebookFeed(getApplicationContext());
        binding.contentScrolling.recyclerView.setAdapter(adapter);
    }

    private void handleInitialNotebookLoading() {
        viewModel.getPaginatedNotebooks().observe(this, notebookEntities -> {
            adapter.submitList(notebookEntities);
        });
    }

    private void handleFabOnClickEvent() {
        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateNotebookActivity.class);
            startActivity(intent);
        });
    }

    private void handleNotebookCardListeners() {
        adapter.setListener(new OnNotebookItemClickListener() {
            @Override
            public void onNoteLongPressed(View view, int position, NotebookCardBinding binding) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
                view.startAnimation(animation);
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
            public void onCloseButtonClicked(View view, int position) {
                view.clearAnimation();
                createDeleteConfirmationPopup(view, position);
            }

            @Override
            public void onNoteClicked(View view, int position) {
                NotebookEntity entity = adapter.getItemByPosition(position);
                if (entity != null) {
                    sendToNotebookActivity(entity.getNotebookId());
                }
            }
        });
    }

    private void sendToNotebookActivity(long id) {
        Intent intent = new Intent(HomeActivity.this, NotebookActivity.class);
        intent.putExtra("notebookId", id);
        startActivity(intent);
    }

    private void createDeleteConfirmationPopup(View view, int position) {
        new MaterialAlertDialogBuilder(view.getRootView().getContext())
                .setTitle("Delete " + adapter.getItemByPosition(position).getTitle() + "?")
                .setMessage("This cannot be undone. " +
                        "Are you sure you would like to delete " +
                        adapter.getItemByPosition(position).getTitle() + "?")
                .setNeutralButton("CANCEL", (dialogInterface, i) ->
                        Toast.makeText(HomeActivity.this, "CANCELLED", Toast.LENGTH_SHORT)
                                .show())
                .setPositiveButton("DELETE", (dialogInterface, i) -> {
                    viewModel.deleteNotebook(adapter.getItemByPosition(position));
                    Toast.makeText(HomeActivity.this, "Deleted " + adapter.getItemByPosition(position).getTitle(), Toast.LENGTH_SHORT)
                            .show();
                })
                .show();
    }
}