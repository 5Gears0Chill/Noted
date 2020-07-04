package com.fivegearszerochill.noted.view.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.NotebookCardBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.interfaces.OnNotebookItemClickListener;

public class NotebookFeedViewHolder extends RecyclerView.ViewHolder {

    private NotebookCardBinding binding;
    private OnNotebookItemClickListener listener;

    public NotebookFeedViewHolder(@NonNull NotebookCardBinding binding, OnNotebookItemClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindTo(NotebookEntity entity) {
        binding.ncDescription.setText(entity.getDescription());
        binding.ncTitle.setText(entity.getTitle());
        binding.ncCard.setCardBackgroundColor(entity.getCardColorId());
        handleOnLongPressedInit();
        handleOnClickButtonInit();
        handleOnNoteClickedInit();

    }

    private void handleOnLongPressedInit() {
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
    }

    private void handleOnClickButtonInit() {
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

    private void handleOnNoteClickedInit() {
        binding.ncCard.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNoteClicked(view, position);
                }
            }
        });
    }
}
