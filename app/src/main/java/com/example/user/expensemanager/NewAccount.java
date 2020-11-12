package com.example.user.expensemanager;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.expensemanager.fragment.DateFragmentShow;
import com.example.user.expensemanager.helper.EasyDb;
import com.example.user.expensemanager.helper.ExpenseContract.ExpenseEntry;
import com.example.user.expensemanager.interfaces.ShowDateListener;

import java.util.Calendar;

public class NewAccount extends AppCompatActivity implements ShowDateListener {
    TextView tvShowDate;
    EditText etAccountName, etBalance;
    Spinner spinner;
    EasyDb easyDb;
    String date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);
        tvShowDate = (TextView) findViewById(R.id.tvShowDate);
        etAccountName = (EditText) findViewById(R.id.etAccountName);
        etBalance = (EditText) findViewById(R.id.etBalance);
        spinner = (Spinner) findViewById(R.id.spChoice);
        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.DAY_OF_MONTH)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.YEAR);
        tvShowDate.setText(date);
        easyDb = new EasyDb(this);

    }

    public void chooseDate(View view) {
        DateFragmentShow d = new DateFragmentShow();
        d.setDateListener(this);
        d.show(getSupportFragmentManager(), "datePicker");
    }



    public void saveProfile(View view) {
        String accountName = etAccountName.getText().toString();
        double balance = Double.parseDouble(etBalance.getText().toString());
        easyDb.createDb();
        easyDb.openDb();

        ContentValues cv = new ContentValues();
        cv.put(ExpenseEntry.USER_NAME, accountName);
        cv.put(ExpenseEntry.USER_BALANCE, balance);
        cv.put(ExpenseEntry.USER_DATE, date);
        long row = easyDb.db.insert(ExpenseEntry.USER_TB_NAME, null, cv);
        if (row > 0) {
            Toast.makeText(this, row + "", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, row + "", Toast.LENGTH_SHORT).show();
        }
        easyDb.closeDb();
        finish();
    }

    public void userSelectedDate(int y, int m, int d) {
        date = d+":"+m+":"+y;
        tvShowDate.setText(date);
    }
}
