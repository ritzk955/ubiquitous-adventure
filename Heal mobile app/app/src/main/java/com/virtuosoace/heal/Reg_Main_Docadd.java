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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reg_Main_Docadd extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth matuh;
    ProgressBar progressBar;
    EditText country, state, city, landmark;
    Button submit, next;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__userinfo2);

        matuh = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.pbar);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Doctor");
        country = (EditText) findViewById(R.id.fcountry);
        state = (EditText) findViewById(R.id.fstate);
        city = (EditText) findViewById(R.id.fcity);
        landmark = (EditText) findViewById(R.id.flandmark);

        ImageButton but1 = (ImageButton) findViewById(R.id.backtouser1);
        Button but2 = (Button) findViewById(R.id.nexttodocadd);

        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.nexttodocadd).setOnClickListener(this);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Reg_Main_Docadd.this, Reg_Main_Docinfo.class);
                startActivity(int1);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Reg_Main_Docadd.this, Reg_Main_Docedu.class);
                startActivity(int2);
            }
        });
    }


    private void pushvalue() {
        String cntry = country.getText().toString().toUpperCase().trim();
        String mstate = state.getText().toString().trim();
        String mcity = city.getText().toString().trim();
        String mlandmark = landmark.getText().toString().trim();

        if (cntry.isEmpty()) {
            country.setError("Cannot be left empty");
            return;
        }
        if (mstate.isEmpty()) {
            state.setError("Cannot be left empty ");
            return;
        }
        if (mcity.isEmpty()) {
            city.setError("Cannot be left empty");
            return;

        }
        if (mlandmark.isEmpty()) {
            landmark.setError("Cannot be left empty");
            return;
        }
        HashMap<String, String> datamap = new HashMap<String, String>();
        datamap.put("Country", cntry);
        datamap.put("State", mstate);
        datamap.put("City", mcity);
        datamap.put("Landmark", mlandmark);
        databaseReference.child("Doctor Info 3").push().setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:

                pushvalue();
                break;
        }

    }

}
