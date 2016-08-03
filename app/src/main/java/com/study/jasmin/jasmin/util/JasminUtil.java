package com.study.jasmin.jasmin.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

/**
 * Created by leesc on 2016-07-25.
 */
public class JasminUtil {
    public static final String TAG = "JasminUtil";

    /*public int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            return ContextCompatApi23.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }*/

    public Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static int[] getScreenSpec(Context context) {
//        int deviceDip;
        int deviceScreenWidth;
        int deviceScreenHeight;
//        int dipHeight;
//        int dipWidth;
//        deviceDip = displayMetrics.density;
        deviceScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        deviceScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
//        dipHeight = (int) (deviceScreenHeight/deviceDip);
//        dipWidth = (int) (deviceScreenWidth/deviceDip);
        int [] spec = {deviceScreenWidth,deviceScreenHeight};
        Log.d(TAG, "deviceScreen : " + deviceScreenWidth + " " + deviceScreenHeight);
//        Log.d(TAG, "dip : " + dipWidth + " " + dipHeight);
        return spec;
    }


}
