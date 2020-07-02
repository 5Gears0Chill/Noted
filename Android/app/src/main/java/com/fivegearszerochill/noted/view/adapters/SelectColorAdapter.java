package com.fivegearszerochill.noted.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.databinding.ColorSelectorCardBinding;
import com.fivegearszerochill.noted.models.ColorModel;
import com.fivegearszerochill.noted.util.general.ResourceHelper;
import com.fivegearszerochill.noted.view.interfaces.OnColorSelectedClickListener;
import com.fivegearszerochill.noted.view.viewholder.SelectedColorViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SelectColorAdapter extends RecyclerView.Adapter<SelectedColorViewHolder> {

    private List<ColorModel> dataSource;
    private Context context;
    private OnColorSelectedClickListener listener;
    private int oldItemPosition = 0;
    private static final String TAG = "SelectColorAdapter";

    public SelectColorAdapter(Context context) {
        this.dataSource = new ArrayList<>();
        this.context = context;
        addAll();
    }

    @NonNull
    @Override
    public SelectedColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ColorSelectorCardBinding binding = ColorSelectorCardBinding.inflate(layoutInflater,parent,false);
        return new SelectedColorViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedColorViewHolder holder, int position) {
        holder.bindTo(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private void addAll(){
       dataSource =  ResourceHelper.getColorResourceList(context);
    }

    public void setListener(OnColorSelectedClickListener listener) {
        this.listener = listener;
    }
}
