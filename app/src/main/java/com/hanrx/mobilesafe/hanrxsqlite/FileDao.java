package com.hanrx.mobilesafe.hanrxsqlite;

import com.hanrx.mobilesafe.hanrxsqlite.db.BaseDao;

public class FileDao extends BaseDao{

    @Override
    protected String createTable() {
        return "create table if not exists tb_file(time varchar(20),path varchar(10),decription varchar(20))";
    }
}
