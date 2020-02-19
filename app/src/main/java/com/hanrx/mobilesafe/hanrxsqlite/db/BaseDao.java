package com.hanrx.mobilesafe.hanrxsqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbFiled;
import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class BaseDao<T> implements IBaseDao<T> {

    /**
     * 持有数据库操作类引用
     */
    private SQLiteDatabase mDatabase;
    /**
     * 保证实例化一次
     */
    private boolean isInit = false;
    /**
     * 持有操作数据库表所对应的java类型
     */
    private Class<T> entityClass;

    private String tableName;

    /**
     * 维护表明和成员变量名的映射关系
     * key -- 表明
     * value -- Filed类型
     */
    private HashMap<String, Field> cacheMap;

    /**
     * @param entity
     * @param sqLiteDatabase
     * @return
     * 实例化一次
     */
    protected boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            entityClass = entity;
            mDatabase = sqLiteDatabase;
            if (entity.getAnnotation(DbTable.class) == null) {
                tableName = entity.getClass().getSimpleName();
            } else {
                tableName = entity.getAnnotation(DbTable.class).value();
            }
            if (!mDatabase.isOpen()) {
                return false;
            }
            if (!TextUtils.isEmpty(createTable())) {
                mDatabase.execSQL(createTable());
            }
            cacheMap = new HashMap<>();
            initCacheMap();
            isInit = true;
        }
        return  isInit;
    }

    /**
     * 维护映射关系
     */
    private void initCacheMap() {
        String sql = "select * from " + this.tableName + " limit 1 , 0";
        Cursor cursor = null;
        try {
            cursor = mDatabase.rawQuery(sql, null);
            //表的列名数组
            String[] columnName = cursor.getColumnNames();
            //拿到Filed数组
            Field[] columnFields = entityClass.getFields();

            for (Field field : columnFields) {
                field.setAccessible(true);
            }
            //开始找对应关系
            for (String columnNames : columnName) {
                //如果找到对应Field就赋值给他
                Field columnField = null;
                for (Field field : columnFields) {
                    String fieldName = null;
                    if (field.getAnnotation(DbFiled.class) != null) {
                        fieldName = field.getAnnotation(DbFiled.class).value();
                    } else {
                        fieldName = field.getName();
                    }
                    //如果表的列名等于了成员变量的注解名字
                    if (columnNames.equals(fieldName)) {
                        columnField = field;
                        break;
                    }
                }
                //找到了对应关系
                if (columnField != null) {
                    cacheMap.put(columnNames, columnField);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

    }

    @Override
    public Long insert(T entity) {
        Map<String, String> map = getValues(entity);
        ContentValues values = getContentValues(map);
        Long result = mDatabase.insert(tableName,null,values);
        return result;
    }

    /**
     * 将map转换成ContentValues
     * @return
     */
    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Set keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }


    private Map<String, String> getValues(T entity) {
        HashMap<String, String> result = new HashMap<>();
        Iterator<Field> filedsIterator = cacheMap.values().iterator();
        //循环遍历映射map的Field
        while (filedsIterator.hasNext()) {
            Field colmunToField = filedsIterator.next();
            String cacheKey = null;
            String cacheValue = null;
            if (colmunToField.getAnnotation(DbFiled.class) != null)  {
                cacheKey = colmunToField.getAnnotation(DbFiled.class).value();
            } else {
                cacheKey = colmunToField.getName();
            }
            try {
                if (null == colmunToField.get(entity)) {
                    continue;
                }
                cacheValue = colmunToField.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.put(cacheKey,cacheValue);
        }
        return result;
    }

    @Override
    public Long update(T entity, T where) {
        return null;
    }

    /**
     * 创建表
     */
    protected abstract String createTable();
}


