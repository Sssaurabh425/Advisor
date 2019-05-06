package com.sam425.advisor;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;


import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class graphActivity extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private PieChart piechart;

    ArrayList<PieEntry> yData;
    ArrayList<String> labels;



    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getInstance().getReference();
        piechart = (PieChart) findViewById(R.id.linechart);
       piechart.setUsePercentValues(true);
       piechart.getDescription().setEnabled(false);
       piechart.setExtraOffsets(5,10,5,15);
       piechart.setDragDecelerationFrictionCoef(0.15f);
       piechart.setDrawHoleEnabled(true);
       piechart.setHoleColor(Color.WHITE);
       piechart.setTransparentCircleRadius(61f);


        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                yData = new ArrayList<>();
                labels = new ArrayList<String>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){

                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);

                    yData.add(new PieEntry(Integer.parseInt(userInformation.useramount()),String.valueOf(userInformation.usermerchant())));


                }

                PieDataSet dataSet = new PieDataSet(yData, "Category");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);

                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                PieData data = new PieData(dataSet);
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.BLACK);

                piechart.setData(data);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        piechart.notifyDataSetChanged();
        piechart.invalidate();
    }


    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}