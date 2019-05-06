package com.sam425.advisor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sam425.advisor.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private BarChart piechart;
    ArrayList<BarEntry> yData;
    ArrayList<String> labels;

    int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chat,container,false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getInstance().getReference();
        piechart = (BarChart) view.findViewById(R.id.linechart);
        Legend l = piechart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis


        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                yData = new ArrayList<>();
                labels = new ArrayList<String>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){

                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);

                    yData.add(new BarEntry(i++,Integer.parseInt(userInformation.useramount())));
                    labels.add(userInformation.usedate_time());


                }

                BarDataSet dataSet = new BarDataSet(yData, "Daily Expenses");
                dataSet.setBarShadowColor(Color.GRAY);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                BarData data = new BarData(dataSet);
                data.setValueTextSize(10f);

                piechart.setData(data);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        piechart.notifyDataSetChanged();
        piechart.invalidate();
        return view;
    }

}
