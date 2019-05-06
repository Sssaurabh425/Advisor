package com.sam425.advisor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sam425.advisor.Earnning.EarningData;
import com.sam425.advisor.Restro.BecomePartnerActivity;
import com.sam425.advisor.Subuser.SubuserData;
import com.sam425.advisor.gallery.Main2Activity;
import com.sam425.advisor.todo.MainTodoActivity;
import com.sam425.advisor.todo.TodoActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
private FloatingActionButton fav;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout,btnFollow;;
    private Button homebtn,graphbutton,expeshow,Thirdactvt,copyText;
    private EditText uidi;
    ViewFlipper v_flipper;
    BottomAppBar bar;
    Dialog myDialog;
    private LinearLayout Camera,sipcalci,voiceactivity,TodoApp;
    private CardView GoalAdd,ExpensesAdd,ExpensesGet,WholeStatement,EarningAdd,EarningGet,AddGoal19,ChatActivity;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    List<UserInformation> userInformations;
    MutualFundInfo mutualFundInfo;
    private ViewPager mViewPager;
    String[] country = { "food","health","transport","grocery","rent","Other"};





    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
            userInformations=new ArrayList<>();

        myDialog = new Dialog(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(getResources().getColor(R.color.white),getResources().getColor(R.color.white));
        tabLayout.setupWithViewPager(mViewPager);
fav=findViewById(R.id.fav);
fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(ProfileActivity.this,ocrscannergirish.class);
        startActivity(i);
    }
});


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();





        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(user.getEmail());


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

uidi=findViewById(R.id.uidi);

        homebtn = (Button) findViewById(R.id.homebutton);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofilewithbtn();

            }
        });

        databaseReference=FirebaseDatabase.getInstance().getReference();

uidi.setText(user.getUid());
       /* copyText = (Button) findViewById(R.id.bCopy);
        copyText.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String getstring = uidi.getText().toString();
                ClipData myClip = ClipData.newPlainText("ID" ,getstring);
                clipboard.setPrimaryClip(myClip);
                //Help to continue :)
                Toast.makeText(getApplicationContext(),"ID COPIED"+ getstring,Toast.LENGTH_SHORT);




            }
        });*/

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ChatFragment();
                    break;
                case 1:
                    fragment = new MessageFragment();
                    break;
                case 2:
                    fragment = new ProfileFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Daily";
                case 1:
                    return "Monthly";
                case 2:
                    return "Category";
            }
            return null;
        }
    }









    public void gotoprofilewithbtn()
    {
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.app_bar_menu,menu);
        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(ProfileActivity.this,"Action View Expanded..",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(ProfileActivity.this,"Action View Collapsed..",Toast.LENGTH_SHORT).show();
                return true;
            }
        };
           return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.home:
                Intent j=new Intent(ProfileActivity.this,ProfileActivity.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




    @Override
    public void onClick(View view) {
        if(view==buttonlogout){
            firebaseAuth.signOut();;
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addexpenses1:
                Intent i=new Intent(ProfileActivity.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(ProfileActivity.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(ProfileActivity.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(ProfileActivity.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(ProfileActivity.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(ProfileActivity.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(ProfileActivity.this,Main2Activity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(ProfileActivity.this,MainTodoActivity.class);
                startActivity(q);
                break;
            case R.id.hireadvisor:
                Intent z=new Intent(ProfileActivity.this,hireadvisor.class);
                startActivity(z);
                break;
            case R.id.account:
                Intent ad=new Intent(ProfileActivity.this,bankpeclick.class);
                startActivity(ad);
                break;
            case R.id.billpayment:

                Intent qa=new Intent(ProfileActivity.this,billgeneration.class);
                startActivity(qa);
                break;

            case R.id.saving:
                Intent t=new Intent(ProfileActivity.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(ProfileActivity.this,ShowMutualFund.class);
                startActivity(u);
                break;
            case R.id.shop:
                Intent uj=new Intent(ProfileActivity.this,shop.class);
                startActivity(uj);
                break;
            case R.id.complaint:
                Intent v=new Intent(ProfileActivity.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(ProfileActivity.this,AddBudget.class);
                startActivity(w);
                break;
            case R.id.logout:
                firebaseAuth.signOut();;
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void ShowPopup(View v) {
        final TextView txtclose,datedaily,timedaily;
        final EditText amtdaily,merselect,catselect;
        final Button datePickerDialogButton,timePickerDialogButton;

        myDialog.setContentView(R.layout.custompopup);
        amtdaily=(EditText) myDialog.findViewById(R.id.dailyamt);
        timedaily=(TextView) myDialog.findViewById(R.id.time1);
        datedaily=(TextView) myDialog.findViewById(R.id.date1);

        catselect=(EditText) myDialog.findViewById(R.id.selctcat);
        final Spinner spin = (Spinner) myDialog.findViewById(R.id.selctmer);
        spin.setOnItemSelectedListener(this);
        String[] country = { "food","health","transport","grocery","rent","Other"};

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        timePickerDialogButton = (Button) myDialog.findViewById(R.id.timePickerDialogButton);
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

                        timedaily.setText(strBuf.toString());
                    }
                };

                Calendar now = Calendar.getInstance();
                int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = now.get(java.util.Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = true;

                TimePickerDialog timePickerDialog = new TimePickerDialog(ProfileActivity.this, onTimeSetListener, hour, minute, is24Hour);

                timePickerDialog.setIcon(R.drawable.ic_message);
                timePickerDialog.setTitle("Please select time.");

                timePickerDialog.show();
            }
        });

        datePickerDialogButton = (Button) myDialog.findViewById(R.id.datePickerDialogButton);
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
                        strBuf.append(month + 1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);

                        datedaily.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                datePickerDialog.setIcon(R.drawable.ic_message);
                datePickerDialog.setTitle("Please select date.");

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = amtdaily.getText().toString().trim();
                String date = datedaily.getText().toString().trim();
                String time = timedaily.getText().toString().trim();
                String id = databaseReference.push().getKey();

                String merchant = spin.getSelectedItem().toString();
                String category = catselect.getText().toString().trim();
                UserInformation userInformation = new UserInformation(date, time, amount, merchant, category);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(ProfileActivity.this, "Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(ProfileActivity.this, "Enter Date"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(time)) {
                    Toast.makeText(ProfileActivity.this, "Enter Time"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(merchant)) {
                    Toast.makeText(ProfileActivity.this, "Enter merchant name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(category)) {
                    Toast.makeText(ProfileActivity.this, "Enter category name"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference.child("user").child(user.getUid()).child("expenses").child(id).setValue(userInformation);
                Toast.makeText(ProfileActivity.this, "Data is Successfully Added"
                        , Toast.LENGTH_SHORT).show();

                amtdaily.setText("");
                datedaily.setText("");
                timedaily.setText("");

                catselect.setText("");
                myDialog.dismiss();
            }

        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}