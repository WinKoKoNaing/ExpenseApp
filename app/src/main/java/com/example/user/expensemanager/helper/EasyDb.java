package com.example.user.expensemanager.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.expensemanager.database.UserHelper;

/**
 * Created by USER on 1/26/2018.
 */

public class EasyDb {
    UserHelper myHelper;
    Context c;
    public SQLiteDatabase db;

    public EasyDb(Context c) {
        this.c = c;
    }

    public void createDb() {
        myHelper = new UserHelper(c);
        db = myHelper.getWritableDatabase();
        db = myHelper.getReadableDatabase();
    }

    public void openDb() {
        myHelper.onOpen(db);
    }

    public void closeDb() {
        myHelper.close();
    }
}
