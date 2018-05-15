package com.virtuosoace.heal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ASUS on 11-05-2018.
 */

public class AddDoctor extends Activity {
    EditText nameMember;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_member);

        nameMember=(EditText)findViewById(R.id.name);
        addButton=(Button)findViewById(R.id.btn_add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=nameMember.getText().toString();
                Bundle b=new Bundle();
                b.putString("username",msg);

                Intent intent= new Intent(AddDoctor.this,Reg_Main_Userinfo3.class);
                // Toast.makeText(AddMember.this, "name:"+message, Toast.LENGTH_LONG).show();

                intent.putExtras(b);

                startActivity(intent);
                //Toast.makeText(AddMember.this, "Your name is "+msg, Toast.LENGTH_SHORT).show();

                AddDoctor.this.finish();
            }
        });
    }
}