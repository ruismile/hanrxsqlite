package com.hanrx.mobilesafe.hanrxsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanrx.mobilesafe.hanrxsqlite.db.BaseDaoFactory;
import com.hanrx.mobilesafe.hanrxsqlite.db.IBaseDao;

public class MainActivity extends AppCompatActivity {

    IBaseDao<User> mBaseDao;

    IBaseDao<FileBean> mFileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBaseDao = BaseDaoFactory.getInstance().getDataHelper(UserDao.class, User.class);
    }

    public void save(View view) {
        User user = new User("teacher", "123456");
        mBaseDao.insert(user);
    }

    public void update(View view) {
        User where = new User();
        where.setName("teacher");
        User user = new User("David", "123456789");
        mBaseDao.update(user,where);
    }

    public void delete(View view) {
        User user = new User();
        user.setName("David");
        mBaseDao.delete(user);
    }
}
