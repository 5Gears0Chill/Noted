package com.fivegearszerochill.noted.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.FragmentRecentNotesBinding;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.view.adapters.RecentNotesFeed;
import com.fivegearszerochill.noted.viewmodel.RecentNotesViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;

public class RecentNotesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecentNotesViewModel viewModel;
    private RecentNotesFeed adapter;
    private FragmentRecentNotesBinding binding;

    public static RecentNotesFragment newInstance(int index) {
        RecentNotesFragment fragment = new RecentNotesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(getActivity()));

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        viewModel = ViewModelParameterizedProvider
                .ofFragment(this, getActivity().getApplicationContext())
                .get(RecentNotesViewModel.class);

        viewModel.getRecentNotesPaged().observe(this, noteAndAttributesAndNotebooks -> {
            if (noteAndAttributesAndNotebooks != null) {
                adapter.submitList(noteAndAttributesAndNotebooks);
            }
        });
    }

    private void init() {
        binding = FragmentRecentNotesBinding.inflate(getLayoutInflater());


        adapter = new RecentNotesFeed(getActivity().getApplicationContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rnRecyclerView.setLayoutManager(manager);
        binding.rnRecyclerView.setAdapter(adapter);
    }
}