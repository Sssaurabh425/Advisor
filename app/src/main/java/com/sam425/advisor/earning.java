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

public class earning extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    private String[] country = { "Rent", "Wages", "Salary", "Bonus", "Other"};
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow;
    private TextView datedaily,timedaily;
    private EditText amtdaily,catselect;
    ;
    private Button homebtn;



    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        this.showDatePickerDialog();

        this.showTimePickerDialog();


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


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
        amtdaily=(EditText) findViewById(R.id.dailyamt);
        timedaily=(TextView) findViewById(R.id.time1);
        datedaily=(TextView) findViewById(R.id.date1);
        Spinner spin = (Spinner) findViewById(R.id.selctmer);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        catselect=(EditText) findViewById(R.id.selctcat);


        btnFollow = (Button) findViewById(R.id.btnfollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = amtdaily.getText().toString().trim();
                String date = datedaily.getText().toString().trim();
                String time = timedaily.getText().toString().trim();
                String id = databaseReference.push().getKey();
                Spinner merselect = (Spinner)findViewById(R.id.selctmer);
                String merchant = merselect.getSelectedItem().toString();
                String category = catselect.getText().toString().trim();
                UserInformation userInformation = new UserInformation(date, time, amount, merchant, category);

                FirebaseUser user = firebaseAuth.getCurrentUser();


                    if (TextUtils.isEmpty(amount)) {
                        Toast.makeText(earning.this, "Enter Amount"
                                , Toast.LENGTH_SHORT).show();
                        return;

                    }


                    if (TextUtils.isEmpty(date)) {
                        Toast.makeText(earning.this, "Enter Date"
                                , Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if (TextUtils.isEmpty(time)) {
                        Toast.makeText(earning.this, "Enter Time"
                                , Toast.LENGTH_SHORT).show();
                        return;

                    }


                        if (TextUtils.isEmpty(merchant)) {
                            Toast.makeText(earning.this, "Enter merchant name"
                                    , Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(category)) {
                            Toast.makeText(earning.this, "Enter category name"
                                    , Toast.LENGTH_SHORT).show();
                            return;
                        }
                        databaseReference.child("user").child(user.getUid()).child("earning").child(id).setValue(userInformation);
                        Toast.makeText(earning.this, "Data is Successfully Added"
                                , Toast.LENGTH_SHORT).show();

                        amtdaily.setText("");
                        datedaily.setText("");
                        timedaily.setText("");

                        catselect.setText("");
                Intent addearn=new Intent(earning.this,ProfileActivity.class);
                startActivity(addearn);



            }
        });


    }




    private void showDatePickerDialog()
    {
        // Get open DatePickerDialog button.
        Button datePickerDialogButton = (Button)findViewById(R.id.datePickerDialogButton);
        datePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();

                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);

                        TextView datePickerValueTextView = (TextView)findViewById(R.id.date1);
                        datePickerValueTextView.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(earning.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                datePickerDialog.setIcon(R.drawable.ic_message);
                datePickerDialog.setTitle("Please select date.");

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }

    // Create and show a TimePickerDialog when click button.
    private void showTimePickerDialog()
    {
        // Get open TimePickerDialog button.
        Button timePickerDialogButton = (Button)findViewById(R.id.timePickerDialogButton);
        timePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnTimeSetListener instance. This listener will be invoked when user click ok button in TimePickerDialog.
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        StringBuffer strBuf = new StringBuffer();

                        strBuf.append(hour);
                        strBuf.append(":");
                        strBuf.append(minute);

                        TextView timePickerValueTextView = (TextView)findViewById(R.id.time1);
                        timePickerValueTextView.setText(strBuf.toString());
                    }
                };

                Calendar now = Calendar.getInstance();
                int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = now.get(java.util.Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = true;

                TimePickerDialog timePickerDialog = new TimePickerDialog(earning.this, onTimeSetListener, hour, minute, is24Hour);

                timePickerDialog.setIcon(R.drawable.ic_message);
                timePickerDialog.setTitle("Please select time.");

                timePickerDialog.show();
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
                Toast.makeText(earning.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(earning.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.action_mf:
                Intent i=new Intent(earning.this,AddMutualFund.class);
                startActivity(i);
                return true;
            case R.id.home:
                Intent j=new Intent(earning.this,ProfileActivity.class);
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
                Intent i=new Intent(earning.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(earning.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(earning.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(earning.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(earning.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(earning.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(earning.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(earning.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(earning.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(earning.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(earning.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(earning.this,AddBudget.class);
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

