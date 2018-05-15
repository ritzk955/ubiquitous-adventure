package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reg_Main_Userinfo1 extends AppCompatActivity implements View.OnClickListener {

    private EditText fullname, emailid, mobno, age;
    private Button fsubmit, fnext;
    private CheckBox cbox, cbox2, cbox3;

    FirebaseAuth mauth;

    ProgressBar progressbar;

    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__userinfo1);

        dbref = FirebaseDatabase.getInstance().getReference().child("Patient");

        progressbar = (ProgressBar) findViewById(R.id.pbar);
        cbox = (CheckBox) findViewById(R.id.checkBox);
        cbox2 = (CheckBox) findViewById(R.id.checkBox3);
        cbox3 = (CheckBox) findViewById(R.id.checkBox4);
        fnext = (Button) findViewById(R.id.nexttodocadd);
        fsubmit = (Button) findViewById(R.id.submit);

        fullname = (EditText) findViewById(R.id.fname);
        mobno = (EditText) findViewById(R.id.fmobno);
        age = (EditText) findViewById(R.id.fage);
        emailid = (EditText) findViewById(R.id.femail);

        mauth = FirebaseAuth.getInstance();
        ImageButton but1 = (ImageButton) findViewById(R.id.backtomain);

        findViewById(R.id.submit).setOnClickListener(this);

        findViewById(R.id.nexttodocadd).setOnClickListener(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pushvalue();

            }
        }, 5);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Reg_Main_Userinfo1.this, LoginMainActivity.class);
                startActivity(int1);
            }
        });

        fnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Reg_Main_Userinfo1.this, Reg_Main_Userinfo2.class);
                startActivity(int2);
            }
        });


    }

    private void registeruser() {
        String email = emailid.getText().toString().trim();
        String mobile = mobno.getText().toString().trim();

        if (email.isEmpty()) {
            emailid.setError("Email is required");
            emailid.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailid.setError("Please enter a valid email");
            emailid.requestFocus();
            return;
        }

        if (!android.util.Patterns.PHONE.matcher(mobile).matches()) {
            mobno.setError("Please enter a valid mobno");
            return;
        }
        progressbar.setVisibility(View.VISIBLE);

        mauth.createUserWithEmailAndPassword(email, mobile).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
                } else if (!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(Reg_Main_Userinfo1.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }


    private void pushvalue() {
        String name = fullname.getText().toString().trim();
        String fage = age.getText().toString().trim();
        String mobile = mobno.getText().toString().trim();

        if (name.isEmpty()) {
            fullname.setError("enter name");
        }

        if (fage.isEmpty()) {
            age.setError("enter age");
        }

        if (mobile.isEmpty()) {
            mobno.setError("enter mobile number");
        }


            HashMap<String, String> datamap = new HashMap<String, String>();
            datamap.put("Name", name);
            datamap.put("Age", fage);
            datamap.put("MobNO", mobile);


            progressbar.setVisibility(View.GONE);

            dbref.child("Patient").child("User Info").push().setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressbar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.submit:
                    registeruser();
                    pushvalue();
                    break;

            }

        }
    }
