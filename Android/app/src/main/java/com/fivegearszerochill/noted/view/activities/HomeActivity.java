package com.fivegearszerochill.noted.view.activities;

import android.content.Intent;
import android.os.Bundle;

import com.fivegearszerochill.noted.databinding.ActivityHomeBinding;
import com.fivegearszerochill.noted.view.adapters.SectionsPagerAdapter;
import com.fivegearszerochill.noted.view.adapters.NotebookFeed;
import com.fivegearszerochill.noted.viewmodel.HomeActivityViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.fivegearszerochill.noted.R;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private HomeActivityViewModel viewModel;
    private ActivityHomeBinding binding;
    private NotebookFeed adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
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
                .get(HomeActivityViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        binding.contentScrolling.recyclerView.setLayoutManager(manager);
        adapter = new NotebookFeed(getApplicationContext());
        binding.contentScrolling.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getPaginatedNotebooks().observe(this, notebookEntities -> {
            adapter.submitList(notebookEntities);
        });

        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent intent = new Intent();
        });
    }
}