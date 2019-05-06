package com.sam425.advisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class sip_calculator extends AppCompatActivity{
    private TextView tv1,tv2,tv3,tv4,tv5,tv6;
    private SeekBar seekBar1,seekBar2,seekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip_calculator);
        seebbarr1();
        seebbarr2();
        seebbarr3();
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);


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
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv1.setText("Covered : " + progress_value1 + " / " + seekBar1.getMax());
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");

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
                        tv2.setText("Covered : " + progress + " / " + seekBar2.getMax());
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv2.setText("Covered : " + progress_value2 + " / " + seekBar2.getMax());
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");

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
                        tv3.setText("Covered : " + progress + " / " + seekBar3.getMax());
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv3.setText("Covered : " + progress_value3 + " / " + seekBar3.getMax());
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv5.setText((seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");
                        tv6.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*seekBar3.getProgress()/100)+"");

                    }
                }
        );

    }




}