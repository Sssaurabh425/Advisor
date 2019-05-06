package com.sam425.advisor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BudgetFixing extends AppCompatActivity{
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv8,datedaily;
    private SeekBar seekBar1,seekBar2,seekBar3,seekBar4,seekBar5,seekBar6;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button addbudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_fixing);
        this.showDatePickerDialog();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        seebbarr1();
        seebbarr2();
        seebbarr3();
        seebbarr4();
        seebbarr5();
        seebbarr6();


        datedaily=(TextView) findViewById(R.id.date1);



        tv8 = (TextView) findViewById(R.id.textView8);
        addbudget=findViewById(R.id.addbudget);
        addbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food= String.valueOf(seekBar1.getProgress());
                String health = String.valueOf(seekBar2.getProgress());
                String transport = String.valueOf(seekBar3.getProgress());
                String grocery=String.valueOf(seekBar4.getProgress());
                String rent = String.valueOf(seekBar5.getProgress());
                String other = String.valueOf(seekBar6.getProgress());
                String overall=tv8.getText().toString().trim();
                String date = datedaily.getText().toString().trim();

                String id = databaseReference.push().getKey();
                UserInformation1 userInformation1 = new UserInformation1(date,food,health,transport,grocery,rent,other,overall);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(BudgetFixing.this, "Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                databaseReference.child("user").child(user.getUid()).child("BudgetFixing").child(id).setValue(userInformation1);
                Toast.makeText(BudgetFixing.this, "Data is Successfully Added"
                        , Toast.LENGTH_SHORT).show();

                Intent addbudget=new Intent(BudgetFixing.this,ProfileActivity.class);
                startActivity(addbudget);
            }
        });


    }

    public void seebbarr1() {
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText("Covered : " + seekBar1.getProgress() + " / " + seekBar1.getMax());

        seekBar1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value1;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value1 = progress;
                        tv1.setText("Covered : " + progress + " / " + seekBar1.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                        }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv1.setText("Covered : " + progress_value1 + " / " + seekBar1.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

    }
    public void seebbarr2() {
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText("Covered : " + seekBar2.getProgress() + " / " + seekBar2.getMax());

        seekBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value2;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value2 = progress;
                        tv2.setText("Covered : " + progress_value2 + " / " + seekBar2.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv2.setText("Covered : " + progress_value2 + " / " + seekBar2.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

    }

    public void seebbarr3() {
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        tv3= (TextView) findViewById(R.id.textView3);
        tv3.setText("Covered : " + seekBar3.getProgress() + " / " + seekBar3.getMax());

        seekBar3.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value3;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value3 = progress;
                        tv3.setText("Covered : " + progress_value3 + " / " + seekBar3.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv3.setText("Covered : " + progress_value3 + " / " + seekBar3.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

    }
    public void seebbarr4() {
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        tv4= (TextView) findViewById(R.id.textView4);
        tv4.setText("Covered : " + seekBar4.getProgress() + " / " + seekBar4.getMax());

        seekBar4.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value4;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value4 = progress;
                        tv4.setText("Covered : " + progress_value4 + " / " + seekBar4.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv4.setText("Covered : " + progress_value4 + " / " + seekBar4.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

    }

    public void seebbarr5() {
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        tv5= (TextView) findViewById(R.id.textView5);
        tv5.setText("Covered : " + seekBar5.getProgress() + " / " + seekBar5.getMax());

        seekBar5.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value5;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value5 = progress;
                        tv5.setText("Covered : " + progress_value5 + " / " + seekBar5.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv5.setText("Covered : " + progress_value5 + " / " + seekBar5.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

    }

    public void seebbarr6() {
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        tv6= (TextView) findViewById(R.id.textView6);
        tv6.setText("Covered : " + seekBar6.getProgress() + " / " + seekBar6.getMax());

        seekBar6.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value6;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value6 = progress;
                        tv6.setText("Covered : " + progress_value6 + " / " + seekBar6.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv6.setText("Covered : " + progress_value6 + " / " + seekBar6.getMax());
                        tv8.setText((seekBar1.getProgress()+ seekBar2.getProgress()+seekBar3.getProgress()+ seekBar4.getProgress()+seekBar5.getProgress()+ seekBar6.getProgress()) +"");

                    }
                }
        );

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(BudgetFixing.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                datePickerDialog.setIcon(R.drawable.ic_message);
                datePickerDialog.setTitle("Please select date.");

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }


}