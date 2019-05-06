package com.sam425.advisor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sam425.advisor.Subuser.SubuserData;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static android.support.constraint.Constraints.TAG;

public class SubUserInfo extends RecyclerView.Adapter<SubUserInfo.SubUserViewHolder> {
    Context context;
    List<UserInformation> userInformations;


    public SubUserInfo(Context context, List<UserInformation> userInformations) {
        this.context = context;
        this.userInformations = userInformations;
    }

    @NonNull
    @Override
    public SubUserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.wholedatalistview,viewGroup,false);
        SubUserViewHolder subUserViewHolder=new SubUserViewHolder(view);
        return subUserViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubUserViewHolder subUserViewHolder, int i) {
final UserInformation userInformation=userInformations.get(i);
subUserViewHolder.subname.setText(userInformation.subname());
        subUserViewHolder.subuid.setText(userInformation.subuid());
        subUserViewHolder.category.setText(userInformation.subcat());
        subUserViewHolder.parentlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick :clicked on"+userInformation.subuid());
                Toast.makeText(context,userInformation.subuid(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,SubuserData.class);
                intent.putExtra("sub_uid",userInformation.subuid());
                context.startActivity(intent);}
        });

    }

    @Override
    public int getItemCount() {
        return userInformations.size();
    }

    public static class SubUserViewHolder extends RecyclerView.ViewHolder{
        TextView subname,subuid,category;
        LinearLayout parentlayout1;
        public SubUserViewHolder(@NonNull View itemView) {
            super(itemView);
            parentlayout1=itemView.findViewById(R.id.parentLayout);
            subname=itemView.findViewById(R.id.subname);
            subuid=itemView.findViewById(R.id.subuid);
            category=itemView.findViewById(R.id.category);

        }
    }

}
