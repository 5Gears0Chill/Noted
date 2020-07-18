package com.fivegearszerochill.noted.view.interfaces;

import android.view.View;

import com.fivegearszerochill.noted.databinding.NoteCardBinding;

public interface OnNoteClickedListener {
    void onEditButtonClicked(View view, int position);
    void onViewButtonClicked(View view, int position);
    void onNoteLongPressed(View view, int position, NoteCardBinding binding);
    void onNoteClicked(View view, int position);
    void onDeleteButtonClicked(View view, int position);
}
