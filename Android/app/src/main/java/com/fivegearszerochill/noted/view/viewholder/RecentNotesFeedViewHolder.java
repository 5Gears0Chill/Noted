package com.fivegearszerochill.noted.view.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.RecentNoteCardBinding;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;

public class RecentNotesFeedViewHolder extends RecyclerView.ViewHolder {

    private RecentNoteCardBinding binding;

    public RecentNotesFeedViewHolder(@NonNull RecentNoteCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bindTo(NoteAndNotebook entity){
        binding.rncTitle.setText(entity.getNote().getTitle());
        binding.rncNotebook.setText(entity.getNotebook().getTitle());
    }
}
