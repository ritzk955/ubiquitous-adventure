package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Reg_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main);

        ImageButton but1=(ImageButton)findViewById(R.id.backtofirst);
        ImageView imgbt1=(ImageView)findViewById(R.id.patient);
        ImageView imgbt2=(ImageView)findViewById(R.id.doc);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int1 = new Intent(Reg_Main.this,LoginMainActivity.class);
                startActivity(int1);

            }
        });

        imgbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Reg_Main.this,Reg_Main_Userinfo1.class);
                startActivity(intent1);
            }
        });

        imgbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Reg_Main.this,Reg_Main_Docinfo.class);
                startActivity(intent2);
            }
        });

    }
}