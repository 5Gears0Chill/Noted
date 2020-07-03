package com.fivegearszerochill.noted.util.mutithreading;

import com.fivegearszerochill.noted.room.dao.CoreDao;
import com.fivegearszerochill.noted.room.entity.CoreEntity;

import java.util.concurrent.Callable;

public class BackgroundInsertTask implements Callable<Long> {
    private final CoreDao dao;
    private final CoreEntity entity;

    public BackgroundInsertTask(CoreDao dao, CoreEntity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public Long call() {
        return dao.insertAsync(entity);
    }
}

