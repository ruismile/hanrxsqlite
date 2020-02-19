package com.hanrx.mobilesafe.hanrxsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanrx.mobilesafe.hanrxsqlite.db.BaseDaoFactory;
import com.hanrx.mobilesafe.hanrxsqlite.db.IBaseDao;

public class MainActivity extends AppCompatActivity {

    IBaseDao<User> mBaseDao;

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
}
