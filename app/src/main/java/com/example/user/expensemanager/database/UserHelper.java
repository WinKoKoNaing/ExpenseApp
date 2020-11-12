package com.example.user.expensemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;

/**
 * Created by USER on 1/24/2018.
 */

public class UserHelper extends SQLiteOpenHelper {
    public UserHelper(Context context) {
        super(context, ExpenseEntry.DB_NAME, null, ExpenseEntry.DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String user_account_create = "CREATE TABLE " + ExpenseEntry.USER_TB_NAME + "(" +
                ExpenseEntry.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ExpenseEntry.USER_NAME + " TEXT NOT NULL," +
                ExpenseEntry.USER_BALANCE + " DOUBLE NOT NULL," +
                ExpenseEntry.USER_DATE + " DATE," +
                ExpenseEntry.INCOME_ID + " INTEGER," +
                ExpenseEntry.EXPENSE_ID + " INTEGER)";
        String user_income = "CREATE TABLE " + ExpenseEntry.INCOME_TB_NAME + "(" +
                ExpenseEntry.INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ExpenseEntry.INCOME_BALANCE + " DOUBLE NOT NULL," +
                ExpenseEntry.INCOME_CATEGORY + " TEXT NOT NULL," +
                ExpenseEntry.INCOME_NOTE + " TEXT," +
                ExpenseEntry.INCOME_CREATE_AT + " CURRENT_TIMESTAMP NOT NULL," +
                ExpenseEntry.EXPENSE_ID + " INTEGER," +
                ExpenseEntry.USER_ID + " INTEGER)";
        String user_expense = "CREATE TABLE " + ExpenseEntry.EXPENSE_TB_NAME + "(" +
                ExpenseEntry.EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ExpenseEntry.EXPENSE_NAME + " TEXT NOT NULL," +
                ExpenseEntry.EXPENSE_BALANCE + " DOUBLE NOT NULL," +
                ExpenseEntry.EXPENSE_CATEGORY + " TEXT NOT NULL," +
                ExpenseEntry.EXPENSE_NOTE + " TEXT," +
                ExpenseEntry.EXPENSE_CREATE_AT + " CURRENT_TIMESTAMP NOT NULL," +
                ExpenseEntry.INCOME_ID + " INTEGER," +
                ExpenseEntry.USER_ID + " INTEGER)";
        db.execSQL(user_account_create);
        db.execSQL(user_income);
        db.execSQL(user_expense);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
