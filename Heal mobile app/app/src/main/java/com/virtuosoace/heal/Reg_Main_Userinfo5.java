package com.virtuosoace.heal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class Reg_Main_Userinfo5 extends AppCompatActivity {
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__userinfo5);

        ImageButton btn1=(ImageButton)findViewById(R.id.backtoinfo4);
        Button btn2=(Button)findViewById(R.id.skiptouserblue);
        Button btn3=(Button)findViewById(R.id.nextofuserblue);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Reg_Main_Userinfo5.this,Reg_Main_Userinfo4.class);
                startActivity(int1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(Reg_Main_Userinfo5.this,Reg_Main_UserBlue.class);
                startActivity(int2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(Reg_Main_Userinfo5.this,Reg_Main_UserBlue.class);
                startActivity(int2);
            }
        });

    }

    public void process(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Uri tempuri=Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0)
        {
            switch (requestCode)
            {
                case Activity.RESULT_OK:
                    if(imageFile.exists())
                    {
                        Toast.makeText(this,"The flie was saved at "+imageFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this,"There was an error saving the file ",Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;

                default:
                    break;

            }
        }
    }
}
