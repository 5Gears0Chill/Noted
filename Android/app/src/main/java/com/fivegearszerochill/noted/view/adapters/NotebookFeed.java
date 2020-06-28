package com.fivegearszerochill.noted.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.fivegearszerochill.noted.databinding.NotebookCardBinding;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.interfaces.OnNoteItemClickListener;
import com.fivegearszerochill.noted.view.viewholder.NotebookFeedViewHolder;

public class NotebookFeed extends PagedListAdapter<NotebookEntity, NotebookFeedViewHolder> {

    private Context context;
    private OnNoteItemClickListener mListener;

    public void setListener(OnNoteItemClickListener mListener) {
        this.mListener = mListener;
    }

    public NotebookFeed(Context context) {
        super(NotebookEntity.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public NotebookFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        NotebookCardBinding binding = NotebookCardBinding.inflate(layoutInflater,parent,false);
        return new NotebookFeedViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookFeedViewHolder holder, int position) {
        NotebookEntity entity = getItem(position);
        if(entity != null) {
            holder.bindTo(entity);
        }
    }
}
