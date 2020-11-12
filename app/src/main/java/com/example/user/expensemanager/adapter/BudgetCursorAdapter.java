package com.example.user.expensemanager.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;

/**
 * Created by USER on 1/26/2018.
 */

public class BudgetCursorAdapter extends CursorAdapter {
    public BudgetCursorAdapter(Context context, Cursor c) {
        super(context, c, 1);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.budget_rows, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvAccountName, tvDate, tvBalance,tvCategory;
        tvAccountName = (TextView) view.findViewById(R.id.tvEName);
        tvDate = (TextView) view.findViewById(R.id.tvEDate);
        tvBalance = (TextView) view.findViewById(R.id.tvEBalance);
        tvCategory = (TextView) view.findViewById(R.id.tvECategory);
        try {
            tvAccountName.setText(cursor.getString(cursor.getColumnIndex(ExpenseEntry.EXPENSE_NAME)));
        }catch (Exception e){
            tvAccountName.setText("Income");
        }
        tvBalance.setText(cursor.getString(cursor.getColumnIndex(ExpenseEntry.EXPENSE_BALANCE)));
        tvDate.setText(cursor.getString(cursor.getColumnIndex(ExpenseEntry.EXPENSE_CREATE_AT)));
        tvCategory.setText(cursor.getString(cursor.getColumnIndex(ExpenseEntry.EXPENSE_CATEGORY)));
    }
}
