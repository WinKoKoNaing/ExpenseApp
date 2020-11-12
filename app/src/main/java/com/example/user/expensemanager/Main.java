package com.example.user.expensemanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.expensemanager.fragment.BarChart;
import com.example.user.expensemanager.fragment.Budget;
import com.example.user.expensemanager.fragment.Events;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;

public class Main extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    EasyDb easyDb;
    TextView tvPName, tvPBalance, tvPDate;
    NavigationView navView;
    View drawer_header;
    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        easyDb = new EasyDb(this);
        navView = (NavigationView)findViewById(R.id.navView);
        drawer_header = navView.getHeaderView(0);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.button_navigation);
        //user info
        tvPName = (TextView)drawer_header.findViewById(R.id.tvPName);
        tvPBalance = (TextView)drawer_header.findViewById(R.id.tvPBalance);
        tvPDate = (TextView)drawer_header.findViewById(R.id.tvPDate);
        //drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opendrawer, R.string.closedrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getUserInfo();
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment f = null;
                switch (item.getItemId()) {
                    case R.id.action_activity:
                        f = new Events();
                        getSupportActionBar().setTitle("Activity");
                        break;
                    case R.id.action_money:
                        f = new Budget();
                        getSupportActionBar().setTitle("Money");
                        break;
                    case R.id.action_barchart:
                        f = new BarChart();
                        getSupportActionBar().setTitle("BarChart");
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, f);
                transaction.commit();
                return true;
            }
        });
        setDefaultFragment();
        getUserInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new Events());
        transaction.commit();
    }


    @SuppressLint("SetTextI18n")
    public void getUserInfo() {
        String name = null,balance=null,date=null;
        easyDb.createDb();
        easyDb.openDb();
        String coloums[] = new String[]{ExpenseEntry.USER_ID, ExpenseEntry.USER_NAME, ExpenseEntry.USER_BALANCE,
                ExpenseEntry.USER_DATE};
        Cursor c = easyDb.db.query(ExpenseEntry.USER_TB_NAME, coloums, null, null, null, null, null);
        if (c.getCount() == 0) {
            startActivity(new Intent(this, NewAccount.class));
        } else {
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                name = c.getString(c.getColumnIndex(ExpenseEntry.USER_NAME));
                balance = c.getString(c.getColumnIndex(ExpenseEntry.USER_BALANCE));
                date = c.getString(c.getColumnIndex(ExpenseEntry.USER_DATE));
            }
            easyDb.closeDb();
        }
        tvPName.setText(name);
        tvPBalance.setText(balance+" kyats");
        tvPDate.setText(date);
    }
}
