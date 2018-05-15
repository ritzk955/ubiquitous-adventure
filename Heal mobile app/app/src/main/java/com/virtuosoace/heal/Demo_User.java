package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Demo_User extends AppCompatActivity {
    private TextView pulserate, humidity, bodytemp;

    FirebaseAuth mauth;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo__user);

        ImageButton but1=(ImageButton)findViewById(R.id.backtomain);
        pulserate=(TextView) findViewById(R.id.pulserate1);
        humidity=(TextView) findViewById(R.id.Humidity);
        bodytemp=(TextView) findViewById(R.id.BodyTemp);

        dbref= FirebaseDatabase.getInstance().getReference("Device1").child("00:21:13:04:1D:4E");




        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Demo_User.this,LoginMainActivity.class);
                startActivity(int1);
            }
        });
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        pulserate.setText(String.valueOf(ds.child("temperature").getValue()));
                        humidity.setText(String.valueOf(ds.child("humidity").getValue()));
                        bodytemp.setText(String.valueOf(ds.child("heartrate").getValue()).trim());
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

    }
}
