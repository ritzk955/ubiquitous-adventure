package com.virtuosoace.heal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Reg_Main_Userinfo4 extends AppCompatActivity {
    private Button btn;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    String str = " ";

    ListView lv;
    List<String> listItems;
    ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__userinfo4);

        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItems);
        lv=(ListView)findViewById(R.id.listView);

        lv.setAdapter(adapter);
        Intent intent = getIntent();

        Bundle b=intent.getExtras();

        if (b != null) {
            str = b.getString("username");
            //Toast.makeText(Reg_Main_Userinfo3.this, "count:"+coun, Toast.LENGTH_LONG).show();
        }
        listItems.add(str);
        adapter.notifyDataSetChanged();

        btn=(Button) findViewById(R.id.button);
        relativeLayout=(RelativeLayout) findViewById(R.id.relative);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1=new Intent(Reg_Main_Userinfo4.this,AddDoctor.class);
                startActivity(int1);
            }
        });

        ImageButton btn1=(ImageButton)findViewById(R.id.backtoinfo3);
        Button btn2=(Button)findViewById(R.id.skiptoinfo5);
        Button btn3=(Button)findViewById(R.id.nexttouserinfo5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Reg_Main_Userinfo4.this,Reg_Main_Userinfo3.class);
                startActivity(int1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(Reg_Main_Userinfo4.this,Reg_Main_Userinfo5.class);
                startActivity(int2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(Reg_Main_Userinfo4.this,Reg_Main_Userinfo5.class);
                startActivity(int2);
            }
        });

    }
}
