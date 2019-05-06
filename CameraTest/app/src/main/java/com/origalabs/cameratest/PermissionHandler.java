package com.origalabs.cameratest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dinesh on 05-09-2018.
 * NOTE: Activity using this library should handle the request code using onRequestPermissionsResult
 */

public class PermissionHandler
{
    private final String TAG = "PermissionHandler";
    public int REQUESTCODE = 1;
    private ArrayList<String> manifestPermission;

    public PermissionHandler()
    {
        manifestPermission = new ArrayList<>();
    }

    public void addPermission(String _permission)
    {
        manifestPermission.add(_permission);
    }

    public ArrayList<String> getPermissionList()
    {
        return manifestPermission;
    }

    public void run(Activity activity)
    {
        if (!isPermissionsGranted(activity))

        {
            ActivityCompat.requestPermissions(activity,
                    manifestPermission.toArray(new String[manifestPermission.size()]),
                    REQUESTCODE);
        }
        else
        {
            Log.i(TAG, "Storage Permission Granted");
        }
    }

    //This method return true if all permission granted
    public boolean isPermissionsGranted(Activity _activity)
    {
        for (String val : manifestPermission)
        {
            if (ContextCompat.checkSelfPermission(_activity,
                    val)
                    != PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }
        return true;
    }
}
