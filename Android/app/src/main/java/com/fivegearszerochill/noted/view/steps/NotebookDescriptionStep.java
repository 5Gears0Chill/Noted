package com.fivegearszerochill.noted.view.steps;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import ernestoyaquello.com.verticalstepperform.Step;

public class NotebookDescriptionStep extends Step<String> {

    private EditText notebookDescriptionEditText;

    public NotebookDescriptionStep(String title) {
        super(title);
    }

    @Override
    public String getStepData() {
        Editable title = notebookDescriptionEditText.getText();
        return title != null ? title.toString() : "";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        // Because the step's data is already a human-readable string, we don't need to convert it.
        // However, we return "(Empty)" if the text is empty to avoid not having any text to display.
        // This string will be displayed in the subtitle of the step whenever the step gets closed.
        String notebookTitle = getStepData();
        return !notebookTitle.isEmpty() ? notebookTitle : "(Empty)";
    }

    @Override
    public void restoreStepData(String data) {
        notebookDescriptionEditText.setText(data);
    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        boolean isTitleValid = stepData.length() <= 50;
        String errorMessage = !isTitleValid ? "50 characters maximum" : "";

        return new IsDataValid(isTitleValid, errorMessage);
    }

    @Override
    protected View createStepContentLayout() {
        notebookDescriptionEditText = new EditText(getContext());
        notebookDescriptionEditText.setSingleLine(true);
        notebookDescriptionEditText.setHint("Describe this note");

        notebookDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Whenever the user updates the user name text, we update the state of the step.
                // The step will be marked as completed only if its data is valid, which will be
                // checked automatically by the form with a call to isStepDataValid().
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return notebookDescriptionEditText;
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
