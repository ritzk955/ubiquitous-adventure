package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reg_Main_Docedu extends AppCompatActivity implements View.OnClickListener {

    private EditText qualification, masters;
    private Button but2;
    FirebaseAuth mauth;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__docedu);

        dbref = FirebaseDatabase.getInstance().getReference().child("Qualification");
        qualification = (EditText) findViewById(R.id.studies);
        masters = (EditText) findViewById(R.id.mainstudies);
        but2= (Button) findViewById(R.id.finish);
        ImageButton but1 = (ImageButton) findViewById(R.id.backtodocadd);
        findViewById(R.id.Button1).setOnClickListener(this);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Reg_Main_Docedu.this, Reg_Main_Docadd.class);
                startActivity(int1);
            }
        });
    }


    private void pushvalue() {
        String education1 = qualification.getText().toString().trim();
        String education2 = masters.getText().toString().trim();

        HashMap<String, String> datamap = new HashMap<String, String>();
        datamap.put("Qualification", education1);
        datamap.put("Master", education2);

        dbref.child("Doctor info 2").setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                pushvalue();
                break;
        }
    }
}