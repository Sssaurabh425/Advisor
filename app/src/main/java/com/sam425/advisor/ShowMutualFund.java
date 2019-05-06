package com.sam425.advisor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMutualFund extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    List<UserInformation> userInformations;
    MutualFundInfo mutualFundInfo;
    RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mutual_fund);
        userInformations=new ArrayList<>();

        listView=(RecyclerView) findViewById(R.id.subuserlist);
        listView.setHasFixedSize(true);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        listView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        databaseReference.child("MutualFund").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                    userInformations.add(userInformation);
                }
                mutualFundInfo = new MutualFundInfo(ShowMutualFund.this, userInformations);
                listView.setAdapter(mutualFundInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
