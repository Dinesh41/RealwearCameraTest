package com.origalabs.cameratest;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by Encoded on 31-03-2018.
 */

public class TakePhotoHandler
{
    private AppCompatActivity appCompatActivity;
    public static final int CAMERA_REQUEST_CODE = 1889;
    private TakePhotoHandlerCallback takePhotoHandlerCallback;

    public TakePhotoHandler(TakePhotoHandlerCallback _takePhotoHandlerCallback)
    {
        takePhotoHandlerCallback = _takePhotoHandlerCallback;
    }

    public void takePhoto(AppCompatActivity _appCompatActivity, String _imageLocation)
    {
        appCompatActivity = _appCompatActivity;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file=new File(_imageLocation);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        appCompatActivity.startActivityForResult(intent, CAMERA_REQUEST_CODE);

    }

    public interface TakePhotoHandlerCallback
    {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

}
