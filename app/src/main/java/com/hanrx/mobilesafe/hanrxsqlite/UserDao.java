package com.hanrx.mobilesafe.hanrxsqlite;

import com.hanrx.mobilesafe.hanrxsqlite.db.BaseDao;

import java.util.List;

public class UserDao extends BaseDao{

    @Override
    protected String createTable() {
        return "create table if not exists tb_user(name varchar(20),password varchar(10))";
    }

    @Override
    public List query(String sql) {

        return null;
    }
}
