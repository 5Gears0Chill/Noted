package com.fivegearszerochill.noted.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.FragmentNotesBinding;
import com.fivegearszerochill.noted.databinding.NoteCardBinding;
import com.fivegearszerochill.noted.view.activities.NotebookActivity;
import com.fivegearszerochill.noted.view.adapters.NoteFeed;
import com.fivegearszerochill.noted.view.interfaces.OnNoteClickedListener;
import com.fivegearszerochill.noted.viewmodel.NoteViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NoteViewModel noteViewModel;
    private NoteFeed adapter;

    private static final String NOTEBOOK_ID = "NOTEBOOK_ID";

    private long notebookId;

    public NotesFragment() {
    }

    /**
     * @param notebookId Notebook ID that the parent activity is instantiated with.
     * @return A new instance of fragment NotesFragment.
     */

    public static NotesFragment newInstance(long notebookId) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putLong(NOTEBOOK_ID, notebookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notebookId = getArguments().getLong(NOTEBOOK_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);

        initViewModel();
        initAdapters();
        handleInitialNoteLoading();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        handleNoteFeedListeners();
    }

    private void initViewModel() {
        noteViewModel = ViewModelParameterizedProvider
                .ofFragment(this, getActivity().getApplicationContext())
                .get(NoteViewModel.class);

    }

    private void initAdapters() {
        adapter = new NoteFeed(getActivity().getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(
                getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        binding.nRecyclerView.setLayoutManager(manager);
        binding.nRecyclerView.setAdapter(adapter);
    }

    private void handleInitialNoteLoading() {
        noteViewModel.getPaginatedNotes(this.notebookId)
                .observe(getViewLifecycleOwner(), noteEntities -> {
                    adapter.submitList(noteEntities);
                });
    }

    private void handleNoteFeedListeners() {
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
                ((NotebookActivity) getActivity()).setAnimationState(true);
                OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        view.clearAnimation();
                        binding.ncCloseButton.setVisibility(View.GONE);
                    }
                };
                getActivity().getOnBackPressedDispatcher().addCallback(callback);
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
                        Toast.makeText(getActivity(), "CANCELLED", Toast.LENGTH_SHORT)
                                .show())
                .setPositiveButton("DELETE", (dialogInterface, i) -> {
//                    noteViewModel.deleteNote(adapter.getItemByPosition(position));
                    Toast.makeText(getActivity(), "Deleted " + adapter.getItemByPosition(position).getTitle(), Toast.LENGTH_SHORT)
                            .show();
                })
                .show();
    }
}