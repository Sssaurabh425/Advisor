package com.sam425.advisor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentProvider;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class voicedeActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DrawerLayout drawer;
    private TextView textViewUserEmail;
    private Button buttonlogout;
    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;
    private Button homebtn;
    private  FloatingActionButton fab;
    ViewFlipper v_flipper;
    BottomAppBar bar;
    Dialog myDialog;


    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicede);

        myDialog = new Dialog(this);
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
       fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                mySpeechRecognizer.startListening(intent);
            }
        });
initializeTextToSpeech();
initializeSpeechRecognizer();

    }
    private void initializeSpeechRecognizer()
    {
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            mySpeechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    List<String> results=bundle.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    );
                    processResult(results.get(0));
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }
    }

    private void processResult(String command) {
        command=command.toLowerCase();
        if(command.indexOf("what")!=-1){
            if(command.indexOf("your name")!=-1){
                speak("My name is sam");
            }

            if(command.indexOf("time")!=-1){
                Date now=new Date();
                String time=DateUtils.formatDateTime(this,now.getTime(),
                        DateUtils.FORMAT_SHOW_TIME);
                speak("The Time now is"+time);
            }
        }
        else if(command.indexOf("open")!=-1){
            if(command.indexOf("welcome page")!=-1){
                Intent addgoal=new Intent(voicedeActivity.this,ProfileActivity.class);
                startActivity(addgoal);
            }
            if(command.indexOf("systematic investment plan calculator")!=-1||command.indexOf("calculator")!=-1){
                Intent addgoal=new Intent(voicedeActivity.this,sip_calculator.class);
                startActivity(addgoal);
            }
            if(command.indexOf("browser")!=-1){
                Intent addgoal=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com"));
                startActivity(addgoal);
            }
        }

    }

    private void initializeTextToSpeech()
    {
        myTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(myTTS.getEngines().size()==0){
                   Toast.makeText(voicedeActivity.this,"There is no TTS engine on your device"
                   ,Toast.LENGTH_LONG).show();
                   finish();
                }
                else{
                    myTTS.setLanguage(Locale.US);
                    speak("Hello! I am ready");
                }
            }
        });
    }

    private void speak(String message)
    {if(Build.VERSION.SDK_INT>=21){
        myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
    }else{
        myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null);
    }
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
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
                Toast.makeText(voicedeActivity.this, "Action View Expanded..", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(voicedeActivity.this, "Action View Collapsed..", Toast.LENGTH_SHORT).show();
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
                Intent i=new Intent(voicedeActivity.this,AddExpenses.class);
                startActivity(i);
                break;
            case R.id.addsubuser:
                Intent j=new Intent(voicedeActivity.this,AddSubUser.class);
                startActivity(j);
                break;
            case R.id.showexpenses:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();*/
                Intent k=new Intent(voicedeActivity.this,ExpensesData.class);
                startActivity(k);
                break;
            case R.id.addearning:
                Intent l=new Intent(voicedeActivity.this,earning.class);
                startActivity(l);
                break;
            case R.id.showearning:
                Intent m=new Intent(voicedeActivity.this,EarningData.class);
                startActivity(m);
                break;
            case R.id.sipcalculator:
                Intent n=new Intent(voicedeActivity.this,sip_calculator.class);
                startActivity(n);
                break;
            case R.id.Savebill:
                Intent p=new Intent(voicedeActivity.this,cameraActivity.class);
                startActivity(p);
                break;
            case R.id.Tasktodo:
                Intent q=new Intent(voicedeActivity.this,MainTodoActivity.class);
                startActivity(q);
                break;


            case R.id.saving:
                Intent t=new Intent(voicedeActivity.this,ExpensesData.class);
                startActivity(t);
                break;
            case R.id.investment:
                Intent u=new Intent(voicedeActivity.this,ExpensesData.class);
                startActivity(u);
                break;
            case R.id.complaint:
                Intent v=new Intent(voicedeActivity.this,RegisterComplaint.class);
                startActivity(v);
                break;
            case R.id.budget:
                Intent w=new Intent(voicedeActivity.this,AddBudget.class);
                startActivity(w);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


