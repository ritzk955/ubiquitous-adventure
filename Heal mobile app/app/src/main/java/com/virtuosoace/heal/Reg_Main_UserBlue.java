package com.virtuosoace.heal;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class Reg_Main_UserBlue extends AppCompatActivity {
    private static final int DISCOVER_DURATION=300;
    private static final int REQUEST_BLU=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg__main__user_blue);

        Button but=(Button)findViewById(R.id.finish);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Reg_Main_UserBlue.this,LoginMainActivity.class);
                startActivity(int1);
            }
        });
    }

    public void bluconnect(View view)
    {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if(btAdapter==null)
        {
            Toast.makeText(this,"Bluetooth is not supported on this device" ,Toast.LENGTH_LONG).show();
        }
        else {
            enableBluetooth();
        }

    }

    private void enableBluetooth() {
        Intent discoveryIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,DISCOVER_DURATION);

        startActivityForResult(discoveryIntent,REQUEST_BLU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==DISCOVER_DURATION && requestCode==REQUEST_BLU){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            File f=new File(Environment.getExternalStorageDirectory(),"md5sum.txt");
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));

            PackageManager pm= getPackageManager();
            List<ResolveInfo> appList=pm.queryIntentActivities(intent,0);

            if(appList.size()>0){
                String packageName =null;
                String className =null;
                boolean found=false;

                for(ResolveInfo info: appList){
                    packageName=info.activityInfo.packageName;
                    if(packageName.equals("com.android.bluetooth")){
                        className=info.activityInfo.name;
                        found=true;
                        break;
                    }
                }
                if(!found){
                    Toast.makeText(this,"Bluetooth havn't been found" ,Toast.LENGTH_LONG).show();
                }else {
                    intent.setClassName(packageName,className);
                    startActivity(intent);
                }
            }else{
                Toast.makeText(this,"Bluetooth is cancelled" ,Toast.LENGTH_LONG).show();
            }
        }
    }
}
