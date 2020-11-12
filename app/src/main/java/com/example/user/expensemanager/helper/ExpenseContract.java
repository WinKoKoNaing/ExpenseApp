package com.example.user.expensemanager.helper;

import android.provider.BaseColumns;

public final class ExpenseContract {
    private ExpenseContract(){}
    public static final class ExpenseEntry implements BaseColumns{
        public static final String DB_NAME = "expensive_manager.db";
        public static final int DB_VERSION = 1;

        // user table
        public static final String USER_TB_NAME = "user";
        public static final String USER_ID = "_uid";
        public static final String USER_NAME = "name";
        public static final String USER_BALANCE = "balance";
        public static final String USER_DATE = "date";
        public static final String USER_EXPENSE_CATEGORY = "expense_category";
        public static final String USER_INCOME_CATEGORY = "income_category";
        // income table
        public static final String INCOME_TB_NAME = "income";
        public static final String INCOME_ID = "_iid";
        public static final String INCOME_CATEGORY = "category";
        public static final String INCOME_BALANCE = "balance";
        public static final String INCOME_NOTE = "note";
        public static final String INCOME_CREATE_AT = "create_at";
        // expense table
        public static final String EXPENSE_TB_NAME = "expense";
        public static final String EXPENSE_ID = "_eid";
        public static final String EXPENSE_NAME = "name";
        public static final String EXPENSE_CATEGORY = "category";
        public static final String EXPENSE_BALANCE = "balance";
        public static final String EXPENSE_NOTE = "note";
        public static final String EXPENSE_CREATE_AT = "create_at";

    }
}
