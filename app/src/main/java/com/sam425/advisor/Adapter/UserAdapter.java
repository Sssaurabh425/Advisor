package com.sam425.advisor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sam425.advisor.MessageActivity;
import com.sam425.advisor.R;
import com.sam425.advisor.UserInformation;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
private Context mContext;
private List<UserInformation> mUsers;
public UserAdapter(Context mContext,List<UserInformation> mUsers){
    this.mUsers=mUsers;
    this.mContext=mContext;
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
final UserInformation userInformation=mUsers.get(position);
holder.username.setText(userInformation.userusn());
if(userInformation.userimageurl().equals("default")){
    holder.profile_image.setImageResource(R.mipmap.ic_launcher);
}
else{
    Glide.with(mContext).load(userInformation.userimageurl()).into(holder.profile_image);
}
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(mContext,MessageActivity.class);
        intent.putExtra("userid",userInformation.userid());
        mContext.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView username;

    public ImageView profile_image;
    public ViewHolder(View itemView){
        super(itemView);
        username=itemView.findViewById(R.id.username);
        profile_image=itemView.findViewById(R.id.profile_image);

    }
}
}
