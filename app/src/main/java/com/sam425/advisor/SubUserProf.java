package com.sam425.advisor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sam425.advisor.Earnning.EarningData;
import com.sam425.advisor.todo.MainTodoActivity;
import com.sam425.advisor.todo.TodoActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SubUserProf extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow;

    ViewFlipper v_flipper;
    BottomAppBar bar;
    Dialog myDialog;
    private LinearLayout Camera, sipcalci, voiceactivity, TodoApp;
    private CardView GoalAdd, ExpensesAdd, ExpensesGet, WholeStatement, EarningAdd, EarningGet;
    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;


    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_user_prof);

        myDialog = new Dialog(this);



        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SubUserLog.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();


        GoalAdd = findViewById(R.id.AddGoal);
        GoalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addgoal = new Intent(SubUserProf.this, SubUserExp.class);
                startActivity(addgoal);
            }
        });



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



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(SubUserProf.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(SubUserProf.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
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
        if (view == buttonlogout) {
            firebaseAuth.signOut();
            ;
            finish();
            startActivity(new Intent(this, firstpage.class));
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(SubUserProf.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(SubUserProf.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(SubUserProf.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(SubUserProf.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(SubUserProf.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(SubUserProf.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(SubUserProf.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(SubUserProf.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(SubUserProf.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(SubUserProf.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(SubUserProf.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(SubUserProf.this,AddBudget.class);
                startActivity(w);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}