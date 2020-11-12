package com.example.user.expensemanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.expensemanager.fragment.DateFragmentShow;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;
import com.example.user.expensemanager.interfaces.ShowDateListener;

import java.util.Calendar;

public class NewItem extends AppCompatActivity implements ShowDateListener {
    Toolbar toolbar;
    TextView tvExpenseDate, tvIncomeDate;
    EditText etExpenseName, etExpenseBalance, etExpenseNote, etIncomeBalance, etIncomeNote;
    ToggleButton tbBudget;
    Spinner spExpense, spIncome;
    LinearLayout lyExpense, lyIncome;
    String name, sp, date, note;
    double balance;
    EasyDb easyDb;
    boolean choice = false;
    Calendar c;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fill each?");
        easyDb = new EasyDb(this);
        lyExpense = (LinearLayout) findViewById(R.id.expense);
        lyIncome = (LinearLayout) findViewById(R.id.income);
        tvExpenseDate = (TextView) findViewById(R.id.tvExpenseDate);
        tvIncomeDate = (TextView) findViewById(R.id.tvIncomeDate);
        etExpenseName = (EditText) findViewById(R.id.etExpenseName);
        etExpenseBalance = (EditText) findViewById(R.id.etExpenseBalance);
        etExpenseNote = (EditText) findViewById(R.id.etExpenseNote);
        etIncomeBalance = (EditText) findViewById(R.id.etIncomeBalance);
        etIncomeNote = (EditText) findViewById(R.id.etIncomeNote);
        spExpense = (Spinner) findViewById(R.id.spExpense);
        spIncome = (Spinner) findViewById(R.id.spIncome);
        tbBudget = (ToggleButton) findViewById(R.id.tbBudget);

        c = Calendar.getInstance();
        date = c.get(Calendar.DAY_OF_MONTH) + ":" + c.get(Calendar.MONTH) + ":" + c.get(Calendar.YEAR);
        tvExpenseDate.setText(date);
        tvIncomeDate.setText(date);


        tbBudget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    lyExpense.setVisibility(View.GONE);
                    lyIncome.setVisibility(View.VISIBLE);
                    choice = b;
                } else {
                    lyExpense.setVisibility(View.VISIBLE);
                    lyIncome.setVisibility(View.GONE);
                    choice = b;
                }
            }
        });
    }

    public void getNewItem() {
        easyDb.createDb();
        easyDb.openDb();
        if (!choice) {
            date = tvExpenseDate.getText().toString();
            name = etExpenseName.getText().toString().trim();
            balance = Double.parseDouble(etExpenseBalance.getText().toString().trim());
            sp = (String) spExpense.getSelectedItem();
            note = etExpenseNote.getText().toString().trim();
            ContentValues cv = new ContentValues();
            cv.put(ExpenseEntry.EXPENSE_NAME, name);
            cv.put(ExpenseEntry.EXPENSE_BALANCE, balance);
            cv.put(ExpenseEntry.EXPENSE_CATEGORY, sp);
            cv.put(ExpenseEntry.EXPENSE_NOTE, note);
            cv.put(ExpenseEntry.EXPENSE_CREATE_AT, date);
            Long id = easyDb.db.insert(ExpenseEntry.EXPENSE_TB_NAME,null,cv);
            if(id>0){
                Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
            }

        } else {
            date = tvIncomeDate.getText().toString();
            balance = Double.parseDouble(etIncomeBalance.getText().toString().trim());
            sp = (String) spIncome.getSelectedItem();
            note = etIncomeNote.getText().toString().trim();
            ContentValues cv = new ContentValues();
            cv.put(ExpenseEntry.INCOME_BALANCE, balance);
            cv.put(ExpenseEntry.INCOME_CATEGORY, sp);
            cv.put(ExpenseEntry.INCOME_NOTE, note);
            cv.put(ExpenseEntry.INCOME_CREATE_AT, date);
            Long id = easyDb.db.insert(ExpenseEntry.INCOME_TB_NAME,null,cv);
            if(id>0){
                Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
            }
        }
        easyDb.closeDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_item_Save:
                getNewItem();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void userSelectedDate(int y, int m, int d) {
        Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show();
        date = d + ":" + m + ":" + y;
        tvExpenseDate.setText(date);
        tvIncomeDate.setText(date);
    }

    public void chooseDate(View view) {
        DateFragmentShow d = new DateFragmentShow();
        d.setDateListener(this);
        d.show(getSupportFragmentManager(), "datePicker");
    }
}
