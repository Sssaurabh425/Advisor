package com.sam425.advisor.Goals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sam425.advisor.ProfileActivity;
import com.sam425.advisor.R;
import com.sam425.advisor.sip_calculator;

public class business extends AppCompatActivity {
private Button btnfollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        btnfollow=findViewById(R.id.btnfollow);
        btnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sip=new Intent(business.this,RestroBusiness.class);
                startActivity(sip);
            }
        });
    }
}
