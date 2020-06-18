package com.fivegearszerochill.noted.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityMainBinding;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.interfaces.OnNoteItemClickListener;
import com.fivegearszerochill.noted.view.recyclerview.NotebookFeed;
import com.fivegearszerochill.noted.viewmodel.MainActivityViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private NotebookFeed adapter;
    private boolean mIsBackVisible = false;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadAnimations();

        viewModel.getPaginatedNotes().observe(this, notebookEntities -> {
            adapter.submitList(notebookEntities);
        });

        adapter.setListener(new OnNoteItemClickListener() {

            @Override
            public void onBookmarkFrontItemClicked(View currentView, View oldView) {
                flip(currentView, oldView);
            }

            @Override
            public void onBookmarkBackItemClicked(View currentView, View oldView) {
                flip(oldView, currentView);
            }
        });
    }

    private void init() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(MainActivityViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.notebookList.setLayoutManager(manager);

        adapter = new NotebookFeed(getApplicationContext());
        binding.notebookList.setAdapter(adapter);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_flip_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_flip_animation);
    }


    private void flip(View front, View back) {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;

        front.setCameraDistance(scale);
        back.setCameraDistance(scale);
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(front);
            mSetLeftIn.setTarget(back);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(back);
            mSetLeftIn.setTarget(front);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }
}