package com.fivegearszerochill.noted.view.steps;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.models.ColorModel;
import com.fivegearszerochill.noted.view.adapters.SelectColorAdapter;
import com.fivegearszerochill.noted.view.interfaces.OnColorSelectedClickListener;

import ernestoyaquello.com.verticalstepperform.Step;

public class NoteCardColorSelectionStep extends Step<ColorModel> {
    private ColorModel model;

    public NoteCardColorSelectionStep(String title) {
        super(title);
    }

    private ColorModel getModel() {
        return model;
    }

    @Override
    public ColorModel getStepData() {
        return getModel();
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return model != null ? model.getColorName().toUpperCase() : "";
    }

    @Override
    public void restoreStepData(ColorModel data) {

    }

    @Override
    protected IsDataValid isStepDataValid(ColorModel stepData) {
        boolean isModelValid = this.model != null;
        return new IsDataValid(isModelValid, "Please select a card color");
    }

    @Override
    protected View createStepContentLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View colorView = inflater.inflate(R.layout.colour_selector, getFormView(), false);
        RecyclerView recyclerView = colorView.findViewById(R.id.cs_recycler_view);
        SelectColorAdapter adapter = new SelectColorAdapter(getFormView().getContext());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setListener((model, position) -> {
            this.model = model;
            markAsCompletedOrUncompleted(true);
            Toast.makeText(getContext(), model.getColorName(), Toast.LENGTH_SHORT).show();
        });
        return colorView;
    }

    @Override
    protected void onStepOpened(boolean animated) {

    }

    @Override
    protected void onStepClosed(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }
}
