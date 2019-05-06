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

public class WholeDataexp extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private TextView tv,tv1;
PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_dataexp);
        firebaseAuth = FirebaseAuth.getInstance();
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv);
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
        final int[] amount = {0};
        databaseReference.child("user").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                              //Getting the data from snapshot
                              UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                              //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                              tv.setText(userInformation.userphoneno());


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final int[] m1 = {0};
    databaseReference.child("user").child(user.getUid()).child("expenses").
            orderByChild("date").startAt("2018-1-").endAt("2018-1-\uf8ff").
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
                        yvalues.add(new PieEntry(m1[0],"Jan"));
                        PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
                orderByChild("date").startAt("2018-2").endAt("2018-2\uf8ff").
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
                            yvalues.add(new PieEntry(m2[0],"Feb"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
                orderByChild("date").startAt("2018-3").endAt("2018-3\uf8ff").
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
                            yvalues.add(new PieEntry(m3[0],"March"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
                orderByChild("date").startAt("2018-4").endAt("2018-4\uf8ff").
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
                            yvalues.add(new PieEntry(m4[0],"April"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
                orderByChild("date").startAt("2018-5").endAt("2018-5\uf8ff").
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
                            yvalues.add(new PieEntry(m5[0],"May"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
                orderByChild("date").startAt("2018-6").endAt("2018-6\uf8ff").
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
                            yvalues.add(new PieEntry(m6[0],"Jun"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m7 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-7").endAt("2018-7\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m7[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m7[0],"July"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m8 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-8").endAt("2018-8\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m8[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m8[0],"Aug"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m9 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-9").endAt("2018-9\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m9[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m9[0],"Sept"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m10 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-10").endAt("2018-10\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m10[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m10[0],"Oct"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m11 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-11").endAt("2018-11\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m11[0] += Integer.parseInt(userInformation.useramount());

                            }
                            yvalues.add(new PieEntry(m11[0],"Nov"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
        final int[] m12 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2018-12").endAt("2018-12\uf8ff").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                //Getting the data from snapshot
                                UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                                //Adding it to a stringString expenses = "Amount: "+dogExpenditure.getAmount()+"\nReason for Use: "+dogExpenditure.getItem()+"\n\n";


                                m12[0] += Integer.parseInt(userInformation.useramount());


                            }
                            yvalues.add(new PieEntry(m12[0],"Dec"));
                            PieDataSet dataSet=new PieDataSet(yvalues,"Monthly data");
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
