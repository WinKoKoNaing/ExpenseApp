package com.example.user.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Long id = getIntent().getExtras().getLong("Id");
        Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
    }
}
