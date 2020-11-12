package com.example.user.expensemanager.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.adapter.BudgetCursorAdapter;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract;


public class IncomeMoney extends Fragment {
    BudgetCursorAdapter adapter;
    View v;
    Toolbar toolbar;
    ListView lvIncome;
    EasyDb easyDb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyDb = new EasyDb(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_income_money, container, false);
        lvIncome = (ListView) v.findViewById(R.id.lvIncome);
        getIncomeBudget();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getIncomeBudget();
    }

    public void getIncomeBudget(){
        easyDb.createDb();
        easyDb.openDb();
        String IncomeColoums[] = new String[]{ExpenseContract.ExpenseEntry.INCOME_ID+" _id", ExpenseContract.ExpenseEntry.INCOME_BALANCE,
                ExpenseContract.ExpenseEntry.INCOME_CATEGORY, ExpenseContract.ExpenseEntry.INCOME_CREATE_AT};
        Cursor income = easyDb.db.query(ExpenseContract.ExpenseEntry.INCOME_TB_NAME, IncomeColoums, null, null, null, null, null);

        adapter = new BudgetCursorAdapter(getContext(),income);
        lvIncome.setAdapter(adapter);
        easyDb.closeDb();
    }
}
