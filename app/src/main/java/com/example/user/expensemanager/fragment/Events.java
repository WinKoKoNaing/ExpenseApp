package com.example.user.expensemanager.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.adapter.BudgetCursorAdapter;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;


public class Events extends Fragment {
    View v,customToast;
    LayoutInflater inflater;

    TextView tvTotalBalance, tvExpenseBalance, tvRemainBalance,
            tvDay, tvMonth, tvWeek;
    double ebalance, tbalance, rbalance;
    PieView pieView;
    EasyDb easyDb;
    BudgetCursorAdapter adapter;
    int cfood,cshop,cmedical,chouse,cfamily,cother,cblack=100;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyDb = new EasyDb(getActivity());
        inflater = getLayoutInflater();

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_activity, container, false);
        setHasOptionsMenu(true);
        customToast = inflater.inflate(R.layout.custom_toast,(ViewGroup)v.findViewById(R.id.custom_toast_layout));
        tvTotalBalance = (TextView) v.findViewById(R.id.tvBalance);
        tvExpenseBalance = (TextView) v.findViewById(R.id.tvExpenseBalance);
        tvRemainBalance = (TextView) v.findViewById(R.id.tvRemainBalance);
        pieView = (PieView) v.findViewById(R.id.pie_view);
        tvDay = (TextView) v.findViewById(R.id.tvDay);
        tvMonth = (TextView) v.findViewById(R.id.tvMonth);
        tvWeek = (TextView) v.findViewById(R.id.tvWeek);
        getallBudget();
        getPieBudget();
        return v;
    }

    @Override
    public void onResume() {
        cblack = 100;
        super.onResume();
        getallBudget();
        getPieBudget();

    }

    public void getallBudget() {
        easyDb.createDb();
        easyDb.openDb();
        String UserColoums[] = new String[]{ExpenseEntry.USER_BALANCE};
//        String ExpenseColoums[] = new String[]{ExpenseEntry.EXPENSE_ID+" _id",ExpenseEntry.EXPENSE_NAME, ExpenseEntry.EXPENSE_BALANCE,
//                ExpenseEntry.EXPENSE_CATEGORY, ExpenseEntry.EXPENSE_CREATE_AT};
        String ExpenseColoums[] = new String[]{ExpenseEntry.EXPENSE_BALANCE,ExpenseEntry.EXPENSE_CATEGORY};
//        String IncomeColoums[] = new String[]{ExpenseEntry.INCOME_ID,ExpenseEntry.INCOME_BALANCE,
//                ExpenseEntry.INCOME_CATEGORY, ExpenseEntry.INCOME_CREATE_AT};
        String IncomeColoums[] = new String[]{ExpenseEntry.INCOME_BALANCE};
        Cursor expense = easyDb.db.query(ExpenseEntry.EXPENSE_TB_NAME, ExpenseColoums, null, null, null, null, null);
        Cursor income = easyDb.db.query(ExpenseEntry.INCOME_TB_NAME, IncomeColoums, null, null, null, null, null);
        Cursor user = easyDb.db.query(ExpenseEntry.USER_TB_NAME, UserColoums, null, null, null, null, null);

        for (expense.moveToFirst(); !expense.isAfterLast(); expense.moveToNext()) {
            ebalance += expense.getDouble(expense.getColumnIndex(ExpenseEntry.EXPENSE_BALANCE));
            String category = expense.getString(expense.getColumnIndex(ExpenseEntry.EXPENSE_CATEGORY));
            switch (category){
                case "Food or Drink":
                    cfood++;
                    break;
                case "Shopping":
                    cshop++;
                    break;
                case "Medical":
                    cmedical++;
                    break;
                case "Housing":
                    chouse++;
                    break;
                case "Family":
                    cfamily++;
                    break;
                case "Other":
                    cother++;
                    break;
            }
        }
        for (income.moveToFirst(); !income.isAfterLast(); income.moveToNext()) {
            tbalance += income.getDouble(income.getColumnIndex(ExpenseEntry.INCOME_BALANCE));
        }
        for (user.moveToFirst(); !user.isAfterLast(); user.moveToNext()) {
            tbalance += user.getDouble(user.getColumnIndex(ExpenseEntry.USER_BALANCE));
        }
        rbalance = tbalance - ebalance;
        tvTotalBalance.setText(tbalance + " kyats");
        tvExpenseBalance.setText(ebalance + " kyats");
        tvRemainBalance.setText(rbalance + " kyats");
        DecimalFormat df = new DecimalFormat("#.##");
        tvDay.setText(df.format((tbalance / 4.2) / 7) + " kyats");
        tvWeek.setText(df.format(tbalance / 4.2) + " kyats");
        tvMonth.setText(df.format(tbalance) + " kyats");
        easyDb.closeDb();
        tbalance = 0;
        ebalance = 0;
        rbalance = 0;
    }

    public void getPieBudget() {
        cblack = cblack-(cfood+cshop+cmedical+chouse+cfamily+cother);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<>();
        pieHelperArrayList.add(new PieHelper(cfood, Color.YELLOW));
        pieHelperArrayList.add(new PieHelper(cshop, Color.MAGENTA));
        pieHelperArrayList.add(new PieHelper(cmedical, Color.RED));
        pieHelperArrayList.add(new PieHelper(chouse, Color.CYAN));
        pieHelperArrayList.add(new PieHelper(cfamily, Color.BLUE));
        pieHelperArrayList.add(new PieHelper(cother, Color.GREEN));
        pieHelperArrayList.add(new PieHelper(cblack, R.color.pie_background));
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(false);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            public void onPieClick(int index) {
                if(index>=0 && index<7){
                    int counts[] = {cfood,cshop,cmedical,chouse,cfamily,cother,cblack};
                    String arg[] = getResources().getStringArray(R.array.expense);
                    TextView name = (TextView)customToast.findViewById(R.id.tvCategoryName);
                    TextView count = (TextView)customToast.findViewById(R.id.tvCount);
                    name.setText(arg[index]);
                    count.setText("Count:"+counts[index]);
//                    Toast t = new Toast(getContext());
//                    t.setDuration(Toast.LENGTH_LONG);
//                    t.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
//                    t.setView(customToast);
//                    t.show();
                }else {
//                    Toast.makeText(getContext(),"None",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
