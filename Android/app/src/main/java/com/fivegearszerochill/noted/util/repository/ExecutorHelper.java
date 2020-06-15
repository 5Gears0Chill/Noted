package com.fivegearszerochill.noted.util.repository;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorHelper {

    private static ExecutorService instance;

    public static synchronized ExecutorService getSingleThreadExecutorInstance(){
        if(instance == null){
            instance = Executors.newSingleThreadExecutor();
        }
        return instance;
    }

    public static Executor getSingleThreadExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static Executor getThreadPool(int size){
        return Executors.newFixedThreadPool(size);
    }

}
