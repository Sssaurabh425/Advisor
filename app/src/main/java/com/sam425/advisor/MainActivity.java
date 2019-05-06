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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnreg;
    private EditText panreg,phonereg;
    private EditText usnreg;
    private EditText emailreg;
    private EditText pswreg;
    private TextView reglog;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        btnreg = (Button) findViewById(R.id.regbtn);
        panreg = (EditText) findViewById(R.id.regpan);
        phonereg = (EditText) findViewById(R.id.regphone);
        usnreg = (EditText) findViewById(R.id.regusn);
        emailreg = (EditText) findViewById(R.id.regemail);
        pswreg = (EditText) findViewById(R.id.regpsw);
        reglog = (TextView) findViewById(R.id.logreg);
        btnreg.setOnClickListener(this);
        reglog.setOnClickListener(this);
    }

    private void registerUser() {
        final String panno = panreg.getText().toString().trim();
        final String phoneno = phonereg.getText().toString().trim();
        final String username = usnreg.getText().toString().trim();
        final String email = emailreg.getText().toString().trim();
        final String password = pswreg.getText().toString().trim();
        if (TextUtils.isEmpty(panno)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phoneno)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter Username", Toast.LENGTH_SHORT).show();
            return;
        }
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
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                            String userid=firebaseUser.getUid();
                            databaseReference=FirebaseDatabase.getInstance().getReference("user").child(userid);
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("panno",panno);
                            hashMap.put("phoneNo",phoneno);
                            hashMap.put("EmailId",email);
                            hashMap.put("imageURL","default");
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                                }
                            });
                              } else {
                            Toast.makeText(MainActivity.this, "Registered Unsuccessful,please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == btnreg) {
            registerUser();
        }
        if (view == reglog) {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
