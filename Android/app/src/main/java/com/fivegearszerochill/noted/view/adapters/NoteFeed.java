package com.fivegearszerochill.noted.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.fivegearszerochill.noted.databinding.NoteCardBinding;
import com.fivegearszerochill.noted.room.entity.NoteEntity;
import com.fivegearszerochill.noted.view.interfaces.OnNoteClickedListener;
import com.fivegearszerochill.noted.view.viewholder.NoteFeedViewHolder;

public class NoteFeed extends PagedListAdapter<NoteEntity, NoteFeedViewHolder> {

    private Context context;
    private OnNoteClickedListener listener;

    public NoteFeed(Context context) {
        super(NoteEntity.DIFF_CALLBACK);
        this.context = context;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        NoteCardBinding binding = NoteCardBinding.inflate(layoutInflater, parent, false);
        return new NoteFeedViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteFeedViewHolder holder, int position) {
        NoteEntity note = getItem(position);
        if (note != null) {
            holder.bindTo(note);
        }
    }

    public NoteEntity getItemByPosition(int position) {
        return getItem(position);
    }
}
