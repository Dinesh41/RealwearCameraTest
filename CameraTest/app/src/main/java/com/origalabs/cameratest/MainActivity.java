package com.origalabs.cameratest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TakePhotoHandler.TakePhotoHandlerCallback, TakeVideoHandler.TakeVideoHandlerCallback
{
    private String imageLocation="/storage/emulated/0/CameraTest/img.jpg";
    private String videoLocation="/storage/emulated/0/CameraTest/vd.mp4";
    private String folder="/storage/emulated/0/CameraTest";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionHandler permissionHandler = new PermissionHandler();
        permissionHandler.addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionHandler.addPermission(Manifest.permission.CAMERA);
        permissionHandler.run(this);
        try{
            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
            m.invoke(null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void createDir()
    {
        File file=new File(folder);
        if(!file.exists())
        {
            file.mkdir();
        }
    }

    public void takePhoto(View view)
    {
        createDir();
        TakePhotoHandler takePhotoHandler=new TakePhotoHandler(this);
        takePhotoHandler.takePhoto(this,imageLocation);
    }
    public void takeVideo(View view)
    {
        createDir();
        TakeVideoHandler takeVideoHandler=new TakeVideoHandler(this);
        takeVideoHandler.recordVideo(this,videoLocation);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1889)//for Take Photo request
        {
            File file=new File(imageLocation);
            if(!file.exists())
            {
                Log.e("ERROR","FILE NOT FOUND "+file.getAbsolutePath());
                Toast.makeText(this,"FILE NOT FOUND "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this,"FILE FOUND "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }
        if(requestCode==1880)//for Take Video request
        {
            File file=new File(videoLocation);
            if(!file.exists())
            {
                Log.e("ERROR","FILE NOT FOUND "+file.getAbsolutePath());
                Toast.makeText(this,"FILE NOT FOUND "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this,"FILE FOUND "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }
    }
}
