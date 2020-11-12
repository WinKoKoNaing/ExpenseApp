package com.example.user.expensemanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.expensemanager.fragment.ExpenseMoney;
import com.example.user.expensemanager.fragment.IncomeMoney;

/**
 * Created by USER on 1/28/2018.
 */

public class BudgetAdapter extends FragmentStatePagerAdapter {
    public BudgetAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position){
            case 0:
                f = new ExpenseMoney();
                break;
            case 1:
                f = new IncomeMoney();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name = null;
        switch (position){
            case 0:
                name = "Expense";
                break;
            case 1:
                name = "Income";
                break;
        }
        return name;
    }
}
