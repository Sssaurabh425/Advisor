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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sam425.advisor.Earnning.EarningData;
import com.sam425.advisor.todo.MainTodoActivity;

import java.util.Calendar;

public class AddMutualFund extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow;

    private EditText Imageurl,Returnper,Year,Fundname,Priority,Cap,Star;

    private Button homebtn;



    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mutual_fund);
        


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonlogout = (Button) findViewById(R.id.buttonLogout);
        buttonlogout.setOnClickListener(this);

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

        databaseReference = FirebaseDatabase.getInstance().getReference("MutualFund");
        Imageurl=(EditText) findViewById(R.id.imageurl);

        Returnper=(EditText) findViewById(R.id.returnper);
        Year=(EditText) findViewById(R.id.year);

        Fundname=(EditText) findViewById(R.id.fundname);
        Priority=(EditText) findViewById(R.id.priority);
        Cap=(EditText) findViewById(R.id.cap);
        Star=(EditText) findViewById(R.id.star);




        btnFollow = (Button) findViewById(R.id.btnfollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageurl = Imageurl.getText().toString().trim();
                String returnper = Returnper.getText().toString().trim();
                String year = Year.getText().toString().trim();
                String fundname = Fundname.getText().toString().trim();
                String priority = Priority.getText().toString().trim();
                String cap = Cap.getText().toString().trim();
                String star = Star.getText().toString().trim();
                String id = databaseReference.push().getKey();



                UserInformation userInformation = new UserInformation(imageurl,returnper,year,fundname,priority,cap,star);

                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (TextUtils.isEmpty(imageurl)) {
                    Toast.makeText(AddMutualFund.this, "Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(year)) {
                    Toast.makeText(AddMutualFund.this, "Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }




                if (TextUtils.isEmpty(returnper)) {
                    Toast.makeText(AddMutualFund.this, "Enter category name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fundname)) {
                    Toast.makeText(AddMutualFund.this, "Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }




                if (TextUtils.isEmpty(priority)) {
                    Toast.makeText(AddMutualFund.this, "Enter category name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }



                if (TextUtils.isEmpty(cap)) {
                    Toast.makeText(AddMutualFund.this, "Enter category name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(star)) {
                    Toast.makeText(AddMutualFund.this, "Enter category name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference.child(id).setValue(userInformation);
                Toast.makeText(AddMutualFund.this, "Data is Successfully Added"
                        , Toast.LENGTH_SHORT).show();


                Intent addexpenses=new Intent(AddMutualFund.this,ProfileActivity.class);
                startActivity(addexpenses);


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
                Toast.makeText(AddMutualFund.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(AddMutualFund.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
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
                Intent i=new Intent(AddMutualFund.this,AddMutualFund.class);
                startActivity(i);
                return true;
            case R.id.home:
                Intent j=new Intent(AddMutualFund.this,ProfileActivity.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void onClick(View view) {
        if (view == buttonlogout) {
            firebaseAuth.signOut();
            ;
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(AddMutualFund.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(AddMutualFund.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(AddMutualFund.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(AddMutualFund.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(AddMutualFund.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(AddMutualFund.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(AddMutualFund.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(AddMutualFund.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(AddMutualFund.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(AddMutualFund.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(AddMutualFund.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(AddMutualFund.this,AddBudget.class);
                startActivity(w);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   
}
