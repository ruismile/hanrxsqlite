package com.hanrx.mobilesafe.hanrxsqlite.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class BaseDaoFactory {
    private String mSqliteDatebasePath;

    private SQLiteDatabase mSQLiteDatabase;

    private static BaseDaoFactory instance = new BaseDaoFactory();

    public BaseDaoFactory() {
        mSqliteDatebasePath = Environment.getDataDirectory().getAbsolutePath() + "user.db";
        openDatebase();
    }

    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz, Class<M> entityClass) {
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass, mSQLiteDatabase);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T)baseDao;
    }

    private void openDatebase() {
        this.mSQLiteDatabase = SQLiteDatabase.openDatabase(mSqliteDatebasePath, null);
    }

    public static BaseDaoFactory getInstance() {
        return instance;
    }
}
