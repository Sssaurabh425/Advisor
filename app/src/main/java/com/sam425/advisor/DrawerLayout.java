package com.sam425.advisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class DrawerLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
}
