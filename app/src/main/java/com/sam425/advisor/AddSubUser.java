package com.sam425.advisor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentProvider;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sam425.advisor.Earnning.EarningData;
import com.sam425.advisor.todo.MainTodoActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSubUser extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    private String[] country = { "child1","child2","wife","Other"};
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow,btnDelete;
    private FirebaseDatabase firebaseDatabase;
    RecyclerView listView;
    List<UserInformation> userInformations;
    private EditText subname,subuid;
SubUserInfo subUserInfo;
    private Button homebtn;



    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_user);

        listView=(RecyclerView) findViewById(R.id.subuserlist);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userInformations=new ArrayList<>();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        homebtn = (Button) findViewById(R.id.homebutton);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofilewithbtn();

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        subname=(EditText) findViewById(R.id.subname);
        subuid=(EditText) findViewById(R.id.subuid);
       Spinner spin = (Spinner) findViewById(R.id.selctmer);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        btnFollow = (Button) findViewById(R.id.btnfollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = subname.getText().toString().trim();
                String uid = subuid.getText().toString().trim();
                String id = databaseReference.push().getKey();

                Spinner merselect = (Spinner)findViewById(R.id.selctmer);
                String merchant = merselect.getSelectedItem().toString();
                UserInformation userInformation = new UserInformation(name,uid,merchant);

                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(AddSubUser.this, "Enter Subuser name"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(uid)) {
                    Toast.makeText(AddSubUser.this, "Enter Subuser UID"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }

                databaseReference.child("user").child(user.getUid()).child("SubUser").child(id).setValue(userInformation);
                Toast.makeText(AddSubUser.this, "Data is Successfully Added"
                        , Toast.LENGTH_SHORT).show();

                subname.setText("");
                subuid.setText("");

                Intent addexpenses=new Intent(AddSubUser.this,ProfileActivity.class);
                startActivity(addexpenses);


            }
        });
        //btnDelete = (Button) findViewById(R.id.btndelete);

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                String uid = subuid.getText().toString();
//
//
//
//
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//
//
//                DatabaseReference cd=FirebaseDatabase.getInstance().getReference("user").child(user.getUid()).child("SubUser").child(uid);
//                cd.removeValue();
//                Toast.makeText(AddSubUser.this,"Sub user is successful deleted",Toast.LENGTH_LONG).show();
//
//
//
//            }
//        });
        databaseReference.child("user").child(user.getUid()).child("SubUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    UserInformation userInformation = userSnapshot.getValue(UserInformation.class);
                    userInformations.add(userInformation);
                }
                subUserInfo = new SubUserInfo(AddSubUser.this, userInformations);
                listView.setAdapter(subUserInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }







    public void gotoprofilewithbtn() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(AddSubUser.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(AddSubUser.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(this,"setting option selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_mf:
                Intent i=new Intent(AddSubUser.this,AddMutualFund.class);
                startActivity(i);
                return true;
            case R.id.home:
                Intent j=new Intent(AddSubUser.this,ProfileActivity.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void onClick(View view) {


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(AddSubUser.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(AddSubUser.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(AddSubUser.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(AddSubUser.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(AddSubUser.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(AddSubUser.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(AddSubUser.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(AddSubUser.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(AddSubUser.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(AddSubUser.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(AddSubUser.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(AddSubUser.this,AddBudget.class);
                startActivity(w);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
