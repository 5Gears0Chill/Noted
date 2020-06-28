package com.fivegearszerochill.noted.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;
import com.fivegearszerochill.noted.view.steps.NotebookTitleStep;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class CreateNotebookActivity extends AppCompatActivity implements StepperFormListener {

    private NotebookTitleStep titleStep;
    private VerticalStepperFormView verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));
        setContentView(R.layout.activity_create_notebook);

        verticalStepperForm = findViewById(R.id.stepper_form);

        titleStep = new NotebookTitleStep("Give your note book a title");

        verticalStepperForm
                .setup(this, titleStep)
                .init();
    }

    @Override
    public void onCompletedForm() {
        Toast.makeText(this, "YOU COMPLETED THE FORM", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelledForm() {
        Toast.makeText(this, "YOU CANCELLED THE FORM", Toast.LENGTH_SHORT).show();
    }
}