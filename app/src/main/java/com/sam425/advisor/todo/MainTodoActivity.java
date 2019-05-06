package com.sam425.advisor.todo;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
import com.sam425.advisor.R;
import com.sam425.advisor.StartActivity;

import java.util.ArrayList;
import java.util.List;

public class MainTodoActivity extends AppCompatActivity {
    ListView listView;
    List<Todo> todos;
    private FirebaseAuth firebaseAuth;
private Button addtask;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_todo);
addtask=(Button) findViewById(R.id.addtask);
addtask.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent at=new Intent(MainTodoActivity.this,TodoActivity.class);
        startActivity(at);
    }
});
        listView=(ListView) findViewById(R.id.list_view3);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        todos=new ArrayList<>();
        String id=databaseReference.push().getKey();

        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child("user").child(user.getUid()).child("Todo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()) {
                    Todo todo = userSnapshot.getValue(Todo.class);
                    todos.add(todo);
                }
                TodoInfo todoInfo = new TodoInfo(MainTodoActivity.this, todos);
                listView.setAdapter(todoInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
