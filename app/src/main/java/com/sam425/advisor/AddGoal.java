package com.sam425.advisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class AddGoal extends AppCompatActivity {
    private CardView individual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        individual=findViewById(R.id.individual);
        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent indi=new Intent(AddGoal.this,IndividualGoal.class);
                startActivity(indi);
            }
        });
    }
}
