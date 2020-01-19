package com.hanrx.mobilesafe.hanrxsqlite;

import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbFiled;
import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbTable;

@DbTable("tb_comment")
public class User {

    @DbFiled("tb_name")
    public String name;

    @DbFiled("tb_password")
    public String password;

}
