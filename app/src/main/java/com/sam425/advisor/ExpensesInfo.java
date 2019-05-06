package com.sam425.advisor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpensesInfo extends ArrayAdapter<UserInformation> {
    private Activity context;
    private List<UserInformation> userInformations;
    public ExpensesInfo(Activity context,List<UserInformation>userInformations){
        super(context,R.layout.list_view,userInformations);
        this.context=context;
        this.userInformations=userInformations;

    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listView =inflater.inflate(R.layout.list_view,null,true);
        TextView date2=(TextView)listView.findViewById(R.id.listdate);
        TextView time2=(TextView)listView.findViewById(R.id.listtime);
        TextView amount2=(TextView)listView.findViewById(R.id.listamt);
        TextView merchant2=(TextView)listView.findViewById(R.id.listmer);
        TextView category2=(TextView)listView.findViewById(R.id.listcat);
        UserInformation userInformation=userInformations.get(position);
        date2.setText(userInformation.usedate());
        time2.setText(userInformation.usertime());
        amount2.setText(userInformation.useramount());
        merchant2.setText(userInformation.usermerchant());
        category2.setText(userInformation.usercategory());
        return listView;
    }
}
