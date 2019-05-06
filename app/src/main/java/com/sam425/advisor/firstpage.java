package com.sam425.advisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstpage extends AppCompatActivity {
private Button Admin,Subuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        Admin=findViewById(R.id.admin);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(firstpage.this,LoginActivity.class);
                startActivity(i);
            }
        });
        Subuser=findViewById(R.id.subuser);
        Subuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(firstpage.this,SubUserLog.class);
                startActivity(i);
            }
        });
    }
}
