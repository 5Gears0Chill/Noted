package com.fivegearszerochill.noted.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.exception.ExceptionMiddleware;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionMiddleware(this));
        setContentView(R.layout.activity_main);


    }
}