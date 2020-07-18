package com.fivegearszerochill.noted.view.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.NoteCardBinding;
import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.util.general.DateHelper;
import com.fivegearszerochill.noted.util.general.StringHelper;
import com.fivegearszerochill.noted.view.interfaces.OnNoteClickedListener;

public class NoteFeedViewHolder extends RecyclerView.ViewHolder {

    private final NoteCardBinding binding;
    private final OnNoteClickedListener listener;

    public NoteFeedViewHolder(@NonNull NoteCardBinding binding, final OnNoteClickedListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindTo(NoteEntity note) {
        binding.nTitle.setText(note.getTitle());
        DraftModel model = StringHelper.parseJsonString(note.getContent());
        binding.nDescription.setText(model.getItems().get(0).getContent());
        binding.nDate.setText(DateHelper.convertDateToString(note.getUpdatedOn()));

        handleOnEditButtonInit();
        handOnNoteLongPressed();
        handleOnDeleteButtonClicked();
        handleOnViewButtonClicked();
        handleOnNoteClicked();
    }

    private void handleOnEditButtonInit() {
        binding.nEditBtn.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onEditButtonClicked(view, position);
                }
            }
        });
    }

    private void handleOnViewButtonClicked() {
        binding.nViewBtn.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onViewButtonClicked(view, position);
                }
            }
        });
    }

    private void handOnNoteLongPressed() {
        binding.card.setOnLongClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    binding.ncCloseButton.setVisibility(View.VISIBLE);
                    listener.onNoteLongPressed(view, position, binding);
                }
            }
            return true;
        });
    }

    private void handleOnDeleteButtonClicked() {
        binding.ncCloseButton.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteButtonClicked(binding.card, position);
                    binding.ncCloseButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void handleOnNoteClicked() {
        binding.card.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNoteClicked(view, position);
                }
            }
        });
    }
}
