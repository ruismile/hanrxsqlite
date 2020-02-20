package com.hanrx.mobilesafe.hanrxsqlite;

import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbFiled;
import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbTable;
@DbTable("tb_user")
public class User {

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(){}

    @DbFiled("name")
    public String name;

    @DbFiled("password")
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
