package com.sam425.advisor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MutualFundInfo extends RecyclerView.Adapter<MutualFundInfo.MutualFundViewHolder> {
        Context context;
        List<UserInformation> userInformations;


public MutualFundInfo(Context context, List<UserInformation> userInformations) {
        this.context = context;
        this.userInformations = userInformations;
        }

@NonNull
@Override
public MutualFundViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.mutualfundlistview,viewGroup,false);
        MutualFundViewHolder mutualFundViewHolder=new MutualFundViewHolder(view);
        return mutualFundViewHolder;
        }

@Override
public void onBindViewHolder(@NonNull MutualFundViewHolder mutualFundViewHolder, int i) {
final UserInformation userInformation=userInformations.get(i);
        mutualFundViewHolder.returnper.setText(userInformation.usereturn());
        mutualFundViewHolder.year.setText(userInformation.useryear());
        mutualFundViewHolder.fundname.setText(userInformation.userfund());
    mutualFundViewHolder.priority.setText(userInformation.useprio());
    mutualFundViewHolder.cap.setText(userInformation.usecap());
    mutualFundViewHolder.star.setText(userInformation.usestar());
    Picasso.with(context).load(userInformation.useimageurl1()).into(mutualFundViewHolder.imageurl);
mutualFundViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d(TAG,"onClick :clicked on"+userInformation.useimageurl1());
        Toast.makeText(context,userInformation.userimageurl(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context,GalleryActivity.class);
        intent.putExtra("image_url",userInformation.useimageurl1());
        intent.putExtra("fund_name",userInformation.userfund());
        intent.putExtra("return",userInformation.usereturn());
        intent.putExtra("year",userInformation.useryear());
        intent.putExtra("priority",userInformation.useprio());
        intent.putExtra("cap",userInformation.usecap());
        intent.putExtra("star",userInformation.usestar());
        context.startActivity(intent);

    }
});

        }

@Override
public int getItemCount() {
        return userInformations.size();
        }

public static class MutualFundViewHolder extends RecyclerView.ViewHolder{
    TextView returnper,year,fundname,priority,cap,star;
    ImageView imageurl;
    LinearLayout parentLayout;
    View mView;
    public MutualFundViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        parentLayout=itemView.findViewById(R.id.parentLayout);
        returnper=itemView.findViewById(R.id.returnper);
        year=itemView.findViewById(R.id.year);
        fundname=itemView.findViewById(R.id.fundname);
        priority=itemView.findViewById(R.id.priority);
        cap=itemView.findViewById(R.id.cap);
        star=itemView.findViewById(R.id.star);
        imageurl=itemView.findViewById(R.id.imageurl);

    }
}

}
