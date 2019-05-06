package com.sam425.advisor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
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

public class DailyActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getInstance().getReference();
        pieChart=findViewById(R.id.piechart1);

        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        final ArrayList<PieEntry> yvalues=new ArrayList<>();

        final int[] m1 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("food").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m1[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m1[0],"Food"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage Data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });

        final int[] m2 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("health").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m2[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m2[0],"Health"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage Data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final int[] m3 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("transport").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m3[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m3[0],"Transport"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final int[] m4 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("grocery").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m4[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m4[0],"Grocery"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final int[] m5 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("rent").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m5[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m5[0],"Rent"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final int[] m6 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("merchant").equalTo("other").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m6[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m6[0],"Other"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Usage data");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);

                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });






    }

}
