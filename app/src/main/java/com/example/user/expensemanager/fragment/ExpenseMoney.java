package com.example.user.expensemanager.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.adapter.BudgetCursorAdapter;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract;


public class ExpenseMoney extends Fragment {
    View v;
    EasyDb easyDb;
    BudgetCursorAdapter adapter;
    ListView lvExpense;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyDb = new EasyDb(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_expense, container, false);
        lvExpense = (ListView)v.findViewById(R.id.lvExpense);
        getExpenseBudget();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getExpenseBudget();

    }

    public void getExpenseBudget(){
        easyDb.createDb();
        easyDb.openDb();
        String ExpenseColoums[] = new String[]{ExpenseContract.ExpenseEntry.EXPENSE_ID+" _id", ExpenseContract.ExpenseEntry.EXPENSE_NAME, ExpenseContract.ExpenseEntry.EXPENSE_BALANCE,
                ExpenseContract.ExpenseEntry.EXPENSE_CATEGORY, ExpenseContract.ExpenseEntry.EXPENSE_CREATE_AT};
        Cursor expense = easyDb.db.query(ExpenseContract.ExpenseEntry.EXPENSE_TB_NAME, ExpenseColoums, null, null, null, null, null);

        adapter = new BudgetCursorAdapter(getContext(),expense);
        lvExpense.setAdapter(adapter);
        easyDb.closeDb();
    }
}
