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
import com.sam425.advisor.Model.Chat;
import com.sam425.advisor.R;
import com.sam425.advisor.UserInformation;

import java.util.ArrayList;
import java.util.List;


public class Chat1Fragment extends Fragment {

private RecyclerView recyclerView;
private UserAdapter userAdapter;
private List<UserInformation> mUsers;
FirebaseUser firebaseUser;
DatabaseReference databaseReference;

private List<String> usersList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_chat1,container,false);
      recyclerView=view.findViewById(R.id.recycler_view);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
      usersList=new ArrayList<>();
      databaseReference=FirebaseDatabase.getInstance().getReference("Chats");
      databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              usersList.clear();
              for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                  Chat chat=snapshot.getValue(Chat.class);
                  if(chat.getSender().equals(firebaseUser.getUid())){
                      usersList.add(chat.getReceiver());

                  }
                  if (chat.getReceiver().equals(firebaseUser.getUid())){
                      usersList.add(chat.getSender());
                  }
              }
              readChats();

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
      return view;
    }
private void readChats(){
        mUsers=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    UserInformation userInformation=snapshot.getValue(UserInformation.class);
                    for(String id:usersList){
                        if(userInformation.userid().equals(id)){
                            if(mUsers.size()!=0){
                                for(UserInformation userInformation1:mUsers){
                                    if(!userInformation.userid().equals(userInformation1.userid())){
                                        mUsers.add(userInformation);
                                    }
                                }
                            }else{
                                mUsers.add(userInformation);
                            }
                        }
                    }
                }
                userAdapter=new UserAdapter(getContext(),mUsers);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}
}
