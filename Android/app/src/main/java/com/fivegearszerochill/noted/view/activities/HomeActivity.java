package com.fivegearszerochill.noted.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityHomeBinding;
import com.fivegearszerochill.noted.view.adapters.SectionsPagerAdapter;
import com.fivegearszerochill.noted.view.adapters.NotebookFeed;
import com.fivegearszerochill.noted.view.interfaces.OnNoteItemClickListener;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity{

    private NotebookViewModel viewModel;
    private ActivityHomeBinding binding;
    private NotebookFeed adapter;

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
            startActivity(new Intent(this, CreateNotebookActivity.class));
        });
    }

    private void handleNotebookCardListeners() {
        adapter.setListener(new OnNoteItemClickListener() {
            @Override
            public void onNoteLongPressed(View view, int position) {
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
                view.startAnimation(animation);
            }

            @Override
            public void onCloseButtonClicked(View view, int position) {
                view.clearAnimation();
            }
        });
    }
}