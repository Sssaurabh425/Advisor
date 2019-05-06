package com.sam425.advisor.Subuser;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.sam425.advisor.ExpensesData;
import com.sam425.advisor.ExpensesInfo;
import com.sam425.advisor.R;
import com.sam425.advisor.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class SubuserData extends AppCompatActivity {
    ListView listView;
    List<UserInformation> userInformations;
    private FirebaseAuth firebaseAuth;
Button b1;
EditText uid;
String asd;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private BarChart piechart;

    ArrayList<BarEntry> yData;
    ArrayList<String> labels;

    int i=0;
    private static final String TAG="GalleryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subuser_data);
        Log.d(TAG,"onCreate:started");
        getIncomingIntent();
        listView=(ListView) findViewById(R.id.list_view2);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        userInformations=new ArrayList<>();
        String id=databaseReference.push().getKey();

b1=findViewById(R.id.b1);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child("user").child(asd).child("expenses").orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()) {
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                    userInformations.add(userInformation);
                }
                ExpensesInfo expensesInfo = new ExpensesInfo(SubuserData.this, userInformations);
                listView.setAdapter(expensesInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getInstance().getReference();
        piechart = (BarChart) findViewById(R.id.linechart);
        Legend l = piechart.getLegend();

        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis



        databaseReference.child("user").child(asd).child("expenses").orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                yData = new ArrayList<>();
                labels = new ArrayList<String>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){

                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);

                    yData.add(new BarEntry(i++,Integer.parseInt(userInformation.useramount())));



                }

                BarDataSet dataSet = new BarDataSet(yData, "# of Calls");
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
    }
});
    }

    private void getIncomingIntent() {
        Log.d(TAG,"getIncominIntent: checking for incoming intents");
        if(getIntent().hasExtra("sub_uid"))
        {
            Log.d(TAG,"getIncomingIntent:found intent extras");
            String sub_uid=getIntent().getStringExtra("sub_uid");
            setImage(sub_uid);
        }
    }
    private void setImage(String sub_uid){
        Log.d(TAG,"setImage:setting to ");

        asd=sub_uid;

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}

