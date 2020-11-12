package com.example.user.expensemanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.expensemanager.fragment.Month;
import com.example.user.expensemanager.fragment.Week;

/**
 * Created by USER on 2/6/2018.
 */

public class BarChartAdapter extends FragmentStatePagerAdapter {
    public BarChartAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position){
            case 0:
                f = new Week();
                break;
            case 1:
                f = new Month();
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
                name = "Weeks";
                break;
            case 1:
                name = "Monthes";
                break;
        }
        return name;
    }
}
