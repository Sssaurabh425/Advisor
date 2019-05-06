package com.sam425.advisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class selectadvisor extends AppCompatActivity {
    private CardView aakash,peter,allson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectadvisor);
        aakash=findViewById(R.id.aakash_info);
        aakash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectadvisor.this,akashbhaukadetaill.class);
                startActivity(i);
            }
        });
        peter=findViewById(R.id.peterinfo);
        peter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectadvisor.this,peterbhau.class);
                startActivity(i);
            }
        });
        allson=findViewById(R.id.allsoninfo);
        allson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectadvisor.this,alisonbahen.class);
                startActivity(i);
            }
        });
    }
}
