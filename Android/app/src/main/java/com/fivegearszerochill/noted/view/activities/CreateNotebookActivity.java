package com.fivegearszerochill.noted.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityCreateNotebookBinding;
import com.fivegearszerochill.noted.databinding.ActivityHomeBinding;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.repository.OnNotebookInsertedCall;
import com.fivegearszerochill.noted.room.entity.NotebookEntity;
import com.fivegearszerochill.noted.view.steps.NoteCardColorSelectionStep;
import com.fivegearszerochill.noted.view.steps.NotebookDescriptionStep;
import com.fivegearszerochill.noted.view.steps.NotebookTitleStep;
import com.fivegearszerochill.noted.viewmodel.NotebookViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;
import com.google.android.material.snackbar.Snackbar;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class CreateNotebookActivity extends AppCompatActivity implements StepperFormListener, OnNotebookInsertedCall {

    private NotebookTitleStep titleStep;
    private NotebookDescriptionStep descriptionStep;
    private NoteCardColorSelectionStep colorSelectionStep;
    private NotebookViewModel viewModel;
    private ActivityCreateNotebookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));

        binding = ActivityCreateNotebookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        initVerticalStepper();
        initViewModel();
    }

    @Override
    public void onCompletedForm() {
        NotebookEntity entity = constructNoteBook();
        viewModel.insertNotebook(entity, this);
    }


    @Override
    public void onCancelledForm() {
        Toast.makeText(this, "YOU CANCELLED THE FORM", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccess() {
        Snackbar.make(
                binding.stepperForm,
                titleStep.getStepData() + " Added",
                Snackbar.LENGTH_LONG)
                .setAction(
                        "OK",
                        view -> startActivity(new Intent(CreateNotebookActivity.this, HomeActivity.class)))
                .show();
    }

    @Override
    public void updateFailure() {
        Snackbar.make(
                binding.stepperForm,
                "Something went wrong",
                Snackbar.LENGTH_LONG)
                .setAction(
                        "RESUBMIT",
                        view -> this.onCompletedForm())
                .show();
    }

    private void initVerticalStepper() {
        titleStep = new NotebookTitleStep("Give your note book a title");
        descriptionStep = new NotebookDescriptionStep("Describe what this notebook will hold");
        colorSelectionStep = new NoteCardColorSelectionStep("Pick a card color");
        binding.stepperForm
                .setup(this, titleStep, descriptionStep, colorSelectionStep)
                .init();
    }

    private void initViewModel() {
        viewModel = ViewModelParameterizedProvider
                .ofActivity(this,getApplicationContext())
                .get(NotebookViewModel.class);
    }

    private NotebookEntity constructNoteBook() {
        return new NotebookEntity(
                titleStep.getStepData(),
                descriptionStep.getStepData(),
                colorSelectionStep.getStepData().getColorId());
    }


}