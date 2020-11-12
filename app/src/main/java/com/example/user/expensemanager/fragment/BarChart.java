package com.example.user.expensemanager.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.adapter.BarChartAdapter;


public class BarChart extends Fragment {
    View v;
    Toolbar toolbar;
    TabLayout tb;
    ViewPager vp;
    BarChartAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bar_chart, container, false);
        tb = (TabLayout)v.findViewById(R.id.tlChart);
        vp = (ViewPager)v.findViewById(R.id.vpChart);
        adapter = new BarChartAdapter(getFragmentManager());
        vp.setAdapter(adapter);

        tb.setupWithViewPager(vp);
        return v;
    }

}
