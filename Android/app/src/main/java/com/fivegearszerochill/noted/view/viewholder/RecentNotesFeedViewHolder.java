package com.fivegearszerochill.noted.view.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.RecentNoteCardBinding;
import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;
import com.fivegearszerochill.noted.util.general.DateHelper;
import com.fivegearszerochill.noted.util.general.StringHelper;

public class RecentNotesFeedViewHolder extends RecyclerView.ViewHolder {

    private RecentNoteCardBinding binding;

    public RecentNotesFeedViewHolder(@NonNull RecentNoteCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bindTo(NoteAndNotebook entity){
        binding.nTitle.setText(entity.getNote().getTitle());
        binding.nDate.setText(DateHelper.convertDateToString(entity.getNote().getUpdatedOn()));
        DraftModel model = StringHelper.parseJsonString(entity.getNote().getContent());
        binding.nDescription.setText(model.getItems().get(0).getContent());
        binding.nNotebook.setText(entity.getNotebook().getTitle());
    }
}
