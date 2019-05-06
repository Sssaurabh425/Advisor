package com.sam425.advisor.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sam425.advisor.Adapter.UserAdapter;
import com.sam425.advisor.R;
import com.sam425.advisor.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<UserInformation> userInformations;
    private UserAdapter userAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_users,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userInformations=new ArrayList<>();
        readUsers();
        // Inflate the layout for this fragment
        return view;  }

    private void readUsers() {

        final FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userInformations.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    UserInformation userInformation=snapshot.getValue(UserInformation.class);
                    assert userInformation!=null;
                    assert firebaseUser!=null;
                    if(!userInformation.userid().equals(firebaseUser.getUid())){
                        userInformations.add(userInformation);
                    }
                }
                userAdapter=new UserAdapter(getContext(),userInformations);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

