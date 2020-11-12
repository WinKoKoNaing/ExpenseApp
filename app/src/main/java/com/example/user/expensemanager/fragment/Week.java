package com.example.user.expensemanager.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import im.dacer.androidcharts.BarView;


public class Week extends Fragment {
    BarView barView;
    View v;
    ArrayList<String> barName;
    ArrayList<Integer> barDataList;
    EasyDb easyDb;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyDb = new EasyDb(getActivity());
        Calendar c = Calendar.getInstance();
        Log.d("WKKN",Calendar.ALL_STYLES+"");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_week, container, false);
        barView = (BarView)v.findViewById(R.id.bar_view);
        getBarView();
        getDayFromDb();
        return v;
    }
    public void getBarView(){
        barName = new ArrayList<>();
        barName.add("SUN");
        barName.add("MON");
        barName.add("TUE");
        barName.add("WED");
        barName.add("THU");
        barName.add("FRI");
        barName.add("SAT");
        barView.setBottomTextList(barName);
        barDataList = new ArrayList<>();
        barDataList.add(3);
        barDataList.add(4);
        barDataList.add(8);
        barDataList.add(10);
        barDataList.add(4);
        barDataList.add(5);
        barDataList.add(8);
        barView.setDataList(barDataList,100);
    }
    public int getCountDay(){
        return 0;
    }
    public void getDayFromDb(){
        easyDb.createDb();
        easyDb.openDb();
        Cursor cursor = easyDb.db.rawQuery("Select balance From expense",null);
        Log.d("WKKN",cursor.getColumnName(0)+"");
        easyDb.closeDb();
    }
}
