package com.hanrx.mobilesafe.hanrxsqlite.db;

public interface IBaseDao<T> {

    /**
     * 插入数据
     * @param entity
     * @return
     */
    Long insert(T entity);

    /**
     *
     * @param entity
     * @param where
     * @return
     */
    Long update(T entity, T where);
}
