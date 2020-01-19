package com.hanrx.mobilesafe.hanrxsqlite.db;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase mDatabase;

    protected boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        mDatabase = sqLiteDatabase;
    }

    @Override
    public Long insert(T entity) {
        return null;
    }

    @Override
    public Long update(T entity, T where) {
        return null;
    }
}


