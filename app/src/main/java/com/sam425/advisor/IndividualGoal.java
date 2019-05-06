package com.sam425.advisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sam425.advisor.Goals.RestroBusiness;
import com.sam425.advisor.Goals.business;

public class IndividualGoal extends AppCompatActivity {
    private LinearLayout business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_goal);
        business=findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sip=new Intent(IndividualGoal.this,business.class);
                startActivity(sip);
            }
        });
    }
}
