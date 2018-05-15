package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 15-03-2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView location, temp, humidity,heartrate;
    DatabaseReference dbref,dbref2,dbref3;
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        heartrate=(TextView) findViewById(R.id.heart);
        temp=(TextView) findViewById(R.id.mtemp);
        humidity=(TextView)findViewById(R.id.mhumid);
        dbref= FirebaseDatabase.getInstance().getReference("Device1").child("00:21:13:04:1D:4E");


        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        temp.setText(String.valueOf(ds.child("temperature").getValue()));
                        humidity.setText(String.valueOf(ds.child("humidity").getValue()));
                        heartrate.setText(String.valueOf(ds.child("heartrate").getValue()).trim());
                    }

                  //  Toast.makeText(getApplicationContext(),"No DATA",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No DATA",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button but1=(Button)findViewById(R.id.medicalLog);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(Login.this,Medical.class);
                startActivity(int1);

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
