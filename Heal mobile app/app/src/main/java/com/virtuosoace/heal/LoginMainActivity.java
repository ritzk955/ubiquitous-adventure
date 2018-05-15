package com.virtuosoace.heal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMainActivity extends Activity implements View.OnClickListener{

    FirebaseAuth mauth;
    EditText emailid,mobno;
    Button login,signup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_main);
        emailid=(EditText) findViewById(R.id.edittextemailid);
        mobno=(EditText) findViewById(R.id.edittextmobno);
        progressBar=(ProgressBar) findViewById(R.id.pbar);
        //Button but1=(Button)findViewById(R.id.loginbtn);
        Button but2=(Button)findViewById(R.id.signupbtn);
        Button but3=(Button)findViewById(R.id.demobtn);
        mauth=FirebaseAuth.getInstance();

        findViewById(R.id.loginbtn).setOnClickListener(this);



         but2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent int2= new Intent(LoginMainActivity.this,Reg_Main.class);
                 startActivity(int2);
             }
         });

         but3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent int3=new Intent(LoginMainActivity.this,Demo_User.class);
                 startActivity(int3);
             }
         });
    }
    private void login(){
        String email = emailid.getText().toString().trim();
        String mob = mobno.getText().toString().trim();

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

        if (mob.isEmpty()) {
            mobno.setError("mobno is required");
            mobno.requestFocus();
            return;
        }

        if (mob.length() < 6) {
            mobno.setError("Minimum lenght of password should be 6");
            mobno.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mauth.signInWithEmailAndPassword(email,mob).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent= new Intent(LoginMainActivity.this,Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"email id or password does not match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbtn:
                login();
                break;
        }

    }
}
