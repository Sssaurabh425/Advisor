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

public class RegisterComplaint extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    private String[] country = { "food","health","transport","grocery","rent","Other"};
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow;
    private TextView datedaily,timedaily;
    private EditText fullname,mobno,emailaddr,subject,message;
    ;
    private Button homebtn;



    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());
  //      buttonlogout = (Button) findViewById(R.id.buttonLogout);
//        buttonlogout.setOnClickListener(this);

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
        fullname=(EditText) findViewById(R.id.fullname);

        mobno=(EditText) findViewById(R.id.mobno);
        emailaddr=(EditText) findViewById(R.id.emailaddr);

        subject=(EditText) findViewById(R.id.subject);
        message=(EditText) findViewById(R.id.message);


        btnFollow = (Button) findViewById(R.id.btnfollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname1 = fullname.getText().toString().trim();
                String mobno1 = mobno.getText().toString().trim();

                String emailaddr1= emailaddr.getText().toString().trim();

                String subject1 = subject.getText().toString().trim();
                String message1 = message.getText().toString().trim();

                String id = databaseReference.push().getKey();


                UserInformation userInformation = new UserInformation(fullname1,mobno1,subject1,message1);

                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (TextUtils.isEmpty(fullname1)) {
                    Toast.makeText(RegisterComplaint.this, "Enter full name"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(mobno1)) {
                    Toast.makeText(RegisterComplaint.this, "Enter mobile no."
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(emailaddr1)) {
                    Toast.makeText(RegisterComplaint.this, "Enter Email address"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(message1)) {
                    Toast.makeText(RegisterComplaint.this, "Enter message"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(subject1)) {
                    Toast.makeText(RegisterComplaint.this, "Enter subject"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference.child("Complaint").child(id).setValue(userInformation);
                Toast.makeText(RegisterComplaint.this, "Your Complaint is registered"
                        , Toast.LENGTH_SHORT).show();



                Intent RegisterComplaint=new Intent(RegisterComplaint.this,ProfileActivity.class);
                startActivity(RegisterComplaint);


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
                Toast.makeText(RegisterComplaint.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(RegisterComplaint.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
              case R.id.action_settings:
                Toast.makeText(this, "setting option selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void onClick(View view) {
       // if (view == buttonlogout) {
         //   firebaseAuth.signOut();
           // ;
            //finish();
            //startActivity(new Intent(this, LoginActivity.class));
        //}


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(RegisterComplaint.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(RegisterComplaint.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(RegisterComplaint.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(RegisterComplaint.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(RegisterComplaint.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(RegisterComplaint.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(RegisterComplaint.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(RegisterComplaint.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(RegisterComplaint.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(RegisterComplaint.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(RegisterComplaint.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(RegisterComplaint.this,AddBudget.class);
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
