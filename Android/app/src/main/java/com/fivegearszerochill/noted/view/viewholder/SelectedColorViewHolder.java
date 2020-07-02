package com.fivegearszerochill.noted.view.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ColorSelectorCardBinding;
import com.fivegearszerochill.noted.models.ColorModel;
import com.fivegearszerochill.noted.view.interfaces.OnColorSelectedClickListener;

public class SelectedColorViewHolder extends RecyclerView.ViewHolder {

    private ColorSelectorCardBinding binding;
    private OnColorSelectedClickListener listener;

    public SelectedColorViewHolder(@NonNull ColorSelectorCardBinding binding, @Nullable OnColorSelectedClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    private static final String TAG = "SelectedColorViewHolder";

    public void bindTo(ColorModel model) {
        binding.cscCard.setCardBackgroundColor(model.getColorId());
        binding.cscCard.setOnClickListener(view -> {
            if (this.listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onColourClicked(model, position);
                }

            }
        });
    }

}
