package com.fivegearszerochill.noted.util.mutithreading;

import com.fivegearszerochill.noted.room.dao.CoreDao;
import com.fivegearszerochill.noted.room.entity.CoreEntity;

import java.util.concurrent.Callable;

public class BackgroundDeleteTask implements Callable<Void> {
    private final CoreDao dao;
    private final CoreEntity entity;

    public BackgroundDeleteTask(CoreDao dao, CoreEntity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public Void call() {
        dao.deleteAsync(entity);
        return null;
    }
}
