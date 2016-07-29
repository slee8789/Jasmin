package com.study.jasmin.jasmin.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

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



}
