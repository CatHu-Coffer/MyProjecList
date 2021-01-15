package com.image.mymemorandum.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by 123 on 2018/1/17.
 */

public class PermissionUlite {

    private Context context;
    private static PermissionUlite permissionUlite;

    private PermissionUlite(Context context) {
        this.context = context;
    }

    public static PermissionUlite getIntance(Context context) {
        if (permissionUlite == null) {
            synchronized (PermissionUlite.class) {
                permissionUlite = new PermissionUlite(context);
                return permissionUlite;
            }
        } else
            return permissionUlite;
    }

    /**
     * 申请相机权限
     *
     * @return
     */
    public PermissionUlite getCreadPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,}, 0);
        }
        return permissionUlite;
    }

    /**
     * 申请读写权限
     *
     * @return
     */
    public PermissionUlite getWreitAndReadPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {//没有授权权限
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }
        return permissionUlite;
    }
}
