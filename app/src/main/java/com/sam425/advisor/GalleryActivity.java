package com.sam425.advisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG="GalleryActivity";
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,return2,year1;
    private Button invest;
    private Float p;
    private int a;
    private CharSequence value;
    private SeekBar seekBar1,seekBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        invest=findViewById(R.id.invest);
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GalleryActivity.this,webpage.class);
                startActivity(i);
            }
        });
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        Log.d(TAG,"onCreate:started");
        getIncomingIntent();

    }
    private void getIncomingIntent(){
        Log.d(TAG,"getIncominIntent: checking for incoming intents");
        if(getIntent().hasExtra("image_url")&&getIntent().hasExtra("fund_name"))
        {
            Log.d(TAG,"getIncomingIntent:found intent extras");
            String imageurl=getIntent().getStringExtra("image_url");
            String fundname=getIntent().getStringExtra("fund_name");
            String return1=getIntent().getStringExtra("return");
            String year=getIntent().getStringExtra("year");
            String priority=getIntent().getStringExtra("priority");
            String cap=getIntent().getStringExtra("cap");
            String star=getIntent().getStringExtra("star");
            setImage(imageurl,fundname,return1,year,priority,cap,star);
        }
    }


    private void setImage(String imageurl,String fundname,String return1,String year,String priority,String cap,String star){
       Log.d(TAG,"setImage:setting to ");
        TextView name=findViewById(R.id.mutualfundname1);
        name.setText(fundname);
        return2=findViewById(R.id.percent1);
        return2.setText(return1);
       year1=findViewById(R.id.year1);
        year1.setText(year);
        TextView priority1=findViewById(R.id.priority1);
        priority1.setText(priority);
        TextView cap1=findViewById(R.id.cap1);
        cap1.setText(cap);
        TextView star1=findViewById(R.id.rating1);
        star1.setText(star);
        ImageView imageView=findViewById(R.id.image);
        Glide.with(this).asBitmap().load(imageurl).into(imageView);

        String maha=return2.getText().toString();
        p = Float.parseFloat(maha);
        String yea=year1.getText().toString();
        a = Integer.parseInt(yea);
        seebbarr1();
        seebbarr2();
    }
    public void seebbarr1() {
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("Covered : " + seekBar1.getProgress() + " / " + seekBar1.getMax());

        seekBar1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value1;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value1 = progress;
                        tv1.setText("Covered : " + progress + " / " + seekBar1.getMax());
                        tv3.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*p/100)+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv1.setText("Covered : " + progress_value1 + " / " + seekBar1.getMax());
                        tv3.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*p/100)+"");

                    }
                }
        );

    }
    public void seebbarr2() {
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setMax(a);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("Covered : " + seekBar2.getProgress() + " / " + seekBar2.getMax());

        seekBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value2;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value2 = progress;
                        tv2.setText("Covered : " + progress + " / " + seekBar2.getMax());
                        tv3.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*p/100)+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv2.setText("Covered : " + progress_value2 + " / " + seekBar2.getMax());
                        tv3.setText((seekBar1.getProgress()*12*seekBar2.getProgress()) +"");
                        tv4.setText((seekBar1.getProgress()*12*seekBar2.getProgress())+(seekBar1.getProgress()*12*seekBar2.getProgress()*p/100)+"");

                    }
                }
        );

    }
}
