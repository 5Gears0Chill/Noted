package com.fivegearszerochill.noted.view.viewholder;

import android.view.View;

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
        binding.ncCard.setCardBackgroundColor(entity.getCardColorId());

        binding.ncCard.setOnLongClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    binding.ncCloseButton.setVisibility(View.VISIBLE);
                    listener.onNoteLongPressed(view, position);
                }
            }
            return true;
        });

        binding.ncCloseButton.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onCloseButtonClicked(binding.ncCard, position);
                    binding.ncCloseButton.setVisibility(View.GONE);
                }
            }
        });
    }
}
