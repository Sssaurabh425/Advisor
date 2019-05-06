package com.sam425.advisor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class ShowBudgetActivity extends AppCompatActivity {
    private static final String TAG="ShowBudgetActivity";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_budget);


        Log.d(TAG,"onCreate:started");
        getIncomingIntent();

    }
    private void getIncomingIntent(){
        Log.d(TAG,"getIncominIntent: checking for incoming intents");
        if(getIntent().hasExtra("budget_date"))
        {
            Log.d(TAG,"getIncomingIntent:found intent extras");
            String date=getIntent().getStringExtra("budget_date");
            String food=getIntent().getStringExtra("budget_food");
            String health=getIntent().getStringExtra("budget_health");
            String transport=getIntent().getStringExtra("budget_transport");
            String grocery=getIntent().getStringExtra("budget_grocery");
            String rent=getIntent().getStringExtra("budget_rent");
            String other=getIntent().getStringExtra("budget_other");
            String overall=getIntent().getStringExtra("budget_overall");
            setImage(date,food,health,transport,grocery,rent,other,overall);
        }
    }
    private void setImage(String date, final String food, String health, String transport, String grocery, String rent, String other, String overall){
        firebaseAuth = FirebaseAuth.getInstance();
TextView dat=findViewById(R.id.dat);
dat.setText(String.valueOf(date));

        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getInstance().getReference();
        final ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.food);
        final TextView min1=(TextView) findViewById(R.id.min1);
        final TextView max1=(TextView) findViewById(R.id.max1);
        simpleProgressBar.setMax(Integer.parseInt(food));
        max1.setText(String.valueOf(food));
        final int[] m1 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("food").
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
                                simpleProgressBar.setProgress(m1[0]);
                                int progressValue = simpleProgressBar.getProgress();
                                min1.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final ProgressBar simpleProgressBar2=(ProgressBar)findViewById(R.id.health);
        final TextView min2=(TextView) findViewById(R.id.min2);
        final TextView max2=(TextView) findViewById(R.id.max2);
        simpleProgressBar2.setMax(Integer.parseInt(health));
        max2.setText(String.valueOf(health));
        final int[] m2 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("health").
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
                            simpleProgressBar2.setProgress(m2[0]);
                            int progressValue = simpleProgressBar2.getProgress();
                            min2.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final ProgressBar simpleProgressBar3=(ProgressBar)findViewById(R.id.transport);
        final TextView min3=(TextView) findViewById(R.id.min3);
        final TextView max3=(TextView) findViewById(R.id.max3);
        simpleProgressBar3.setMax(Integer.parseInt(transport));
        max3.setText(String.valueOf(transport));
        final int[] m3 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("transport").
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
                            simpleProgressBar3.setProgress(m3[0]);
                            int progressValue = simpleProgressBar3.getProgress();
                            min3.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final ProgressBar simpleProgressBar4=(ProgressBar)findViewById(R.id.grocery);
        final TextView min4=(TextView) findViewById(R.id.min4);
        final TextView max4=(TextView) findViewById(R.id.max4);
        simpleProgressBar4.setMax(Integer.parseInt(grocery));
        max4.setText(String.valueOf(grocery));
        final int[] m4 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("grocery").
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
                            simpleProgressBar4.setProgress(m4[0]);
                            int progressValue = simpleProgressBar4.getProgress();
                            min4.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final ProgressBar simpleProgressBar5=(ProgressBar)findViewById(R.id.rent);
        final TextView min5=(TextView) findViewById(R.id.min5);
        final TextView max5=(TextView) findViewById(R.id.max5);
        simpleProgressBar5.setMax(Integer.parseInt(rent));
        max5.setText(String.valueOf(rent));
        final int[] m5 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("rent").
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
                            simpleProgressBar5.setProgress(m5[0]);
                            int progressValue = simpleProgressBar5.getProgress();
                            min5.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
        final ProgressBar simpleProgressBar6=(ProgressBar)findViewById(R.id.other);
        final TextView min6=(TextView) findViewById(R.id.min6);
        final TextView max6=(TextView) findViewById(R.id.max6);
        simpleProgressBar6.setMax(Integer.parseInt(other));
        max6.setText(String.valueOf(other));
        final int[] m6 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").orderByChild("merchant").equalTo("Other").
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
                            simpleProgressBar6.setProgress(m6[0]);
                            int progressValue = simpleProgressBar6.getProgress();
                            min6.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });

        final ProgressBar simpleProgressBar7=(ProgressBar)findViewById(R.id.overall);
        final TextView min7=(TextView) findViewById(R.id.min6);
        final TextView max7=(TextView) findViewById(R.id.max6);
        simpleProgressBar7.setMax(Integer.parseInt(overall));
        max7.setText(String.valueOf(overall));
        final int[] m7 = {0};
        databaseReference.child("user").child(user.getUid()).child("expenses").
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
                            simpleProgressBar7.setProgress(m7[0]);
                            int progressValue = simpleProgressBar7.getProgress();
                            min7.setText(String.valueOf(progressValue));



                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//Log your error here.
                    }
                });
    }
}
