package com.fivegearszerochill.noted.view.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.NotebookCardBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.interfaces.OnNoteItemClickListener;

public class NotebookFeedViewHolder extends RecyclerView.ViewHolder {

    private NotebookCardBinding binding;
    private OnNoteItemClickListener listener;

    public NotebookFeedViewHolder(@NonNull NotebookCardBinding binding, OnNoteItemClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindTo(NotebookEntity entity) {
        binding.ncDescription.setText(entity.getDescription());
        binding.ncTitle.setText(entity.getTitle());

    }
}
