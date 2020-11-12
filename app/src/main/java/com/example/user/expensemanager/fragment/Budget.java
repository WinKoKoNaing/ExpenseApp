package com.example.user.expensemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.expensemanager.NewItem;
import com.example.user.expensemanager.R;
import com.example.user.expensemanager.adapter.BudgetAdapter;

public class Budget extends Fragment {
    View v;
    TabLayout tl;
    ViewPager vp;
    BudgetAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_budget, container, false);
        setHasOptionsMenu(true);
        tl = (TabLayout)v.findViewById(R.id.tl);
        vp = (ViewPager)v.findViewById(R.id.vp);
        adapter = new BudgetAdapter(getFragmentManager());
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.budget_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_newItem:
                startActivity(new Intent(getContext(), NewItem.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
