package com.fivegearszerochill.noted.view.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.NoteCardBinding;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.util.general.DateHelper;
import com.fivegearszerochill.noted.util.general.StringHelper;
import com.fivegearszerochill.noted.view.interfaces.OnNoteClickedListener;

public class NoteFeedViewHolder extends RecyclerView.ViewHolder {

    private NoteCardBinding binding;
    private OnNoteClickedListener listener;

    public NoteFeedViewHolder(@NonNull NoteCardBinding binding, OnNoteClickedListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindTo(NoteEntity note) {
        binding.nTitle.setText(note.getTitle());
        binding.nDescription.setText(StringHelper.getFirstNWords(note.getContent(), 10));
        binding.nDate.setText(DateHelper.convertDateToString(note.getUpdatedOn()));
        handleOnEditButtonInit();
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
}
