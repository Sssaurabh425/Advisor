
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



import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class BudgetInfo extends RecyclerView.Adapter<BudgetInfo.BudgetViewHolder> {
    Context context;
    List<UserInformation1> userInformations1;


    public BudgetInfo(Context context, List<UserInformation1> userInformations1) {
        this.context = context;
        this.userInformations1 = userInformations1;
    }

    @NonNull
    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.budgetlayout,viewGroup,false);
        BudgetViewHolder budgetViewHolder=new BudgetViewHolder(view);
        return budgetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder budgetViewHolder, int i) {
        final UserInformation1 userInformation1=userInformations1.get(i);
        budgetViewHolder.date.setText(userInformation1.usedate());
        budgetViewHolder.food.setText(userInformation1.usefood());
        budgetViewHolder.transport.setText(userInformation1.usetransport());
        budgetViewHolder.grocery.setText(userInformation1.usegrocery());
        budgetViewHolder.rent.setText(userInformation1.userent());
        budgetViewHolder.other.setText(userInformation1.useother());
        budgetViewHolder.overall.setText(userInformation1.useoverall());
        budgetViewHolder.health.setText(userInformation1.usehealth());

        budgetViewHolder.parentlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick :clicked on"+userInformation1.usedate());
                Toast.makeText(context,userInformation1.usedate(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,ShowBudgetActivity.class);
                intent.putExtra("budget_date",userInformation1.usedate());
                intent.putExtra("budget_food",userInformation1.usefood());
                intent.putExtra("budget_health",userInformation1.usehealth());
                intent.putExtra("budget_transport",userInformation1.usetransport());
                intent.putExtra("budget_grocery",userInformation1.usegrocery());
                intent.putExtra("budget_rent",userInformation1.userent());
                intent.putExtra("budget_other",userInformation1.useother());
                intent.putExtra("budget_overall",userInformation1.useoverall());

                context.startActivity(intent);}
        });

    }

    @Override
    public int getItemCount() {
        return userInformations1.size();
    }

    public static class BudgetViewHolder extends RecyclerView.ViewHolder{
        TextView date,overall,food,health,grocery,rent,other,transport;
        LinearLayout parentlayout1;
        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);
            parentlayout1=itemView.findViewById(R.id.parentLayout);
            date=itemView.findViewById(R.id.date);
            overall=itemView.findViewById(R.id.overall);
            food=itemView.findViewById(R.id.food);
            health=itemView.findViewById(R.id.health);
            grocery=itemView.findViewById(R.id.grocery);
            rent=itemView.findViewById(R.id.rent);
            other=itemView.findViewById(R.id.other);
            transport=itemView.findViewById(R.id.transport);

        }
    }

}
