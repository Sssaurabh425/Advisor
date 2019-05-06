package com.sam425.advisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class hireadvisor extends AppCompatActivity {
private LinearLayout advi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hireadvisor);
        advi=findViewById(R.id.advi);
        advi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(hireadvisor.this,selectadvisor.class);
                startActivity(i);
            }
        });
    }
}
