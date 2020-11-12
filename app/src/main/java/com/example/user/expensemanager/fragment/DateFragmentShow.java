package com.example.user.expensemanager.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.user.expensemanager.R;
import com.example.user.expensemanager.interfaces.ShowDateListener;

import java.util.Calendar;

/**
 * Created by USER on 1/24/2018.
 */

public class DateFragmentShow extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    ShowDateListener dateListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getContext(), R.style.DatePicker, this, year, month, day);
    }

    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Toast.makeText(getContext(), i + "" + i1 + " " + i2, Toast.LENGTH_SHORT).show();
        dateListener.userSelectedDate(i, i1, i2);
    }


    public void setDateListener(ShowDateListener dateListener) {
        this.dateListener = dateListener;
    }
}
