package com.sam425.advisor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnlog;
    private EditText emaillog;
    private EditText pswlog;
    private TextView reggoto;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
                finish();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        emaillog = (EditText) findViewById(R.id.logemail);
        pswlog = (EditText) findViewById(R.id.logpsw);
        btnlog = (Button) findViewById(R.id.logbtn);
        progressDialog =new ProgressDialog(this);
        reggoto = (TextView) findViewById(R.id.gotoreg);
        btnlog.setOnClickListener(this);
        reggoto.setOnClickListener(this);
    }

    private void userlogin() {
        String email = emaillog.getText().toString().trim();
        String password = pswlog.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){

                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    }
                }});

    }


    @Override
    public void onClick(View view) {
        if (view == btnlog) {
            userlogin();

        }
        if (view == reggoto) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }}