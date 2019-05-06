package com.sam425.advisor.todo;

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
import com.sam425.advisor.AddBudget;
import com.sam425.advisor.AddExpenses;
import com.sam425.advisor.AddSubUser;
import com.sam425.advisor.ChatFragment;
import com.sam425.advisor.Earnning.EarningData;
import com.sam425.advisor.ExpensesData;
import com.sam425.advisor.LoginActivity;
import com.sam425.advisor.MessageFragment;
import com.sam425.advisor.ProfileActivity;
import com.sam425.advisor.ProfileFragment;
import com.sam425.advisor.R;
import com.sam425.advisor.RegisterComplaint;
import com.sam425.advisor.UserInformation;
import com.sam425.advisor.cameraActivity;
import com.sam425.advisor.earning;
import com.sam425.advisor.sip_calculator;

import java.util.Calendar;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    private String[] country = { "High priority","Medium Priority","low priority"};
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout, btnFollow;
    private TextView datedaily,timedaily;
    private EditText taskname,taskdesc;
    ;
    private Button homebtn;



    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        this.showDatePickerDialog();

        this.showTimePickerDialog();


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

        databaseReference = FirebaseDatabase.getInstance().getReference();
        taskname=(EditText) findViewById(R.id.taskname);
        taskdesc=(EditText) findViewById(R.id.taskdesc);
        timedaily=(TextView) findViewById(R.id.time1);
        datedaily=(TextView) findViewById(R.id.date1);
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

                String taskdes = taskdesc.getText().toString().trim();
                String tasknam = taskname.getText().toString().trim();
                String date = datedaily.getText().toString().trim();
                String time = timedaily.getText().toString().trim();
                String id = databaseReference.push().getKey();
                Spinner priority = (Spinner)findViewById(R.id.selctmer);
                String taskpriority = priority.getSelectedItem().toString();

                Todo todo = new Todo(tasknam,taskdes,date,time,taskpriority);

                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (TextUtils.isEmpty(tasknam)) {
                    Toast.makeText(TodoActivity.this, "Enter Task To Do"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(taskdes)) {
                    Toast.makeText(TodoActivity.this, "Enter Description for task"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(TodoActivity.this, "Enter Date"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(time)) {
                    Toast.makeText(TodoActivity.this, "Enter Time"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(taskpriority)) {
                    Toast.makeText(TodoActivity.this, "Enter Priority for task"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("user").child(user.getUid()).child("Todo").child(id).setValue(todo);
                Toast.makeText(TodoActivity.this, "Data is Successfully Added"
                        , Toast.LENGTH_SHORT).show();

                taskname.setText("");
                taskdesc.setText("");
                datedaily.setText("");
                timedaily.setText("");
                Intent bt=new Intent(TodoActivity.this,MainTodoActivity.class);
                startActivity(bt);



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
                DatePickerDialog datePickerDialog = new DatePickerDialog(TodoActivity.this, onDateSetListener, year, month, day);

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

                TimePickerDialog timePickerDialog = new TimePickerDialog(TodoActivity.this, onTimeSetListener, hour, minute, is24Hour);

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
                Toast.makeText(TodoActivity.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(TodoActivity.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(this, LoginActivity.class));
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(TodoActivity.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(TodoActivity.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(TodoActivity.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(TodoActivity.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(TodoActivity.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(TodoActivity.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(TodoActivity.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(TodoActivity.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(TodoActivity.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(TodoActivity.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(TodoActivity.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(TodoActivity.this,AddBudget.class);
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
