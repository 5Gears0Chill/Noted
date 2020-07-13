package com.fivegearszerochill.noted.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.fivegearszerochill.noted.databinding.RecentNoteCardBinding;
import com.fivegearszerochill.noted.room.entity.queryable.NoteAndNotebook;
import com.fivegearszerochill.noted.view.viewholder.RecentNotesFeedViewHolder;

public class RecentNotesFeed extends PagedListAdapter<NoteAndNotebook, RecentNotesFeedViewHolder> {

    private Context context;

    public RecentNotesFeed(Context context){
        super(NoteAndNotebook.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecentNotesFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        RecentNoteCardBinding binding = RecentNoteCardBinding.inflate(layoutInflater,parent,false);
        return new RecentNotesFeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentNotesFeedViewHolder holder, int position) {
        NoteAndNotebook entity = getItem(position);
        if(entity != null){
            holder.bindTo(entity);
        }
    }
}
