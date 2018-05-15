package com.virtuosoace.heal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageButton;

/**
 * Created by Admin on 17-03-2018.
 */

public class Medical extends AppCompatActivity{
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.medicallog);

        gridLayout=(GridLayout) findViewById(R.id.grid);

        setSingleEvent(gridLayout);

        ImageButton but1=(ImageButton)findViewById(R.id.backtoLogin);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Medical.this,Login.class);
                startActivity(int1);
            }
        });
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent int1=new Intent(Medical.this,Medical_Report.class);
                    startActivity(int1);
                }
            });
        }
    }
}
