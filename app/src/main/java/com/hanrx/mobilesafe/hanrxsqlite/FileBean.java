package com.hanrx.mobilesafe.hanrxsqlite;

import com.hanrx.mobilesafe.hanrxsqlite.db.annotion.DbTable;

@DbTable("tb_file")
public class FileBean {
    public String time;
    public String path;
    public String decripte;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDecripte() {
        return decripte;
    }

    public void setDecripte(String decripte) {
        this.decripte = decripte;
    }




}
