package com.fivegearszerochill.noted.view.interfaces;

import android.view.View;

import com.fivegearszerochill.noted.databinding.NotebookCardBinding;

public interface OnNotebookItemClickListener {
    void onNoteLongPressed(View view, int position, NotebookCardBinding binding);
    void onCloseButtonClicked(View view,int position);
    void onNoteClicked(View view, int position);
}
