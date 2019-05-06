package com.origalabs.cameratest;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by Dinesh on 24-10-2018.
 */

public class TakeVideoHandler
{
    private AppCompatActivity appCompatActivity;
    public static final int VIDEO_REQUEST_CODE = 1880;
    private TakeVideoHandler.TakeVideoHandlerCallback takeVideoHandlerCallback;

    public TakeVideoHandler(TakeVideoHandler.TakeVideoHandlerCallback _takeVideoHandlerCallback)
    {
        takeVideoHandlerCallback = _takeVideoHandlerCallback;
    }

    public void recordVideo(AppCompatActivity _appCompatActivity, String _videoLocation)
    {
        appCompatActivity = _appCompatActivity;
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file=new File(_videoLocation);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        appCompatActivity.startActivityForResult(intent, VIDEO_REQUEST_CODE);

    }

    public interface TakeVideoHandlerCallback
    {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

}
