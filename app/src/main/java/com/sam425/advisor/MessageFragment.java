package com.sam425.advisor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.sam425.advisor.R;

import java.util.ArrayList;

public class MessageFragment extends Fragment {
    View v;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_message,container,false);
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getInstance().getReference();
        pieChart= view.findViewById(R.id.piechart1);
        pieChart.setDrawCenterText(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        //pieChart.setBackground(R.drawable.backgroun);
        pieChart.setTransparentCircleRadius(61f);
        final ArrayList<PieEntry> yvalues=new ArrayList<>();

        final int[] m1 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
                orderByChild("date").startAt("2019-1-").endAt("2019-1-\uf8ff").
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

                            dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
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
                orderByChild("date").startAt("2019-2").endAt("2019-2\uf8ff").
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
                orderByChild("date").startAt("2019-3").endAt("2019-3\uf8ff").
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
                orderByChild("date").startAt("2019-4").endAt("2019-4\uf8ff").
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
                orderByChild("date").startAt("2019-5").endAt("2019-5\uf8ff").
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
                orderByChild("date").startAt("2019-6").endAt("2019-6\uf8ff").
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
                orderByChild("date").startAt("2019-7").endAt("2019-7\uf8ff").
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

                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);
                            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                            data.setValueTextColor(Color.BLACK);
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
                orderByChild("date").startAt("2019-8").endAt("2019-8\uf8ff").
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
                orderByChild("date").startAt("2019-9").endAt("2019-9\uf8ff").
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
                orderByChild("date").startAt("2019-10").endAt("2019-10\uf8ff").
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
                orderByChild("date").startAt("2019-11").endAt("2019-11\uf8ff").
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
                orderByChild("date").startAt("2019-12").endAt("2019-12\uf8ff").
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

                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            PieData data=new PieData(dataSet);
                            data.setValueTextSize(10f);
                            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                            data.setValueTextColor(Color.BLACK);
                            pieChart.setData(data);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });




        return view;

    }


}
