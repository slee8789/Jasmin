package com.study.jasmin.jasmin.util;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by leesc on 2016-07-25.
 */
public class JasminUtil {
    public static final String TAG = "JasminUtil";
    public static InputMethodManager keyboardManager;
    /*public int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            return ContextCompatApi23.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }*/

    /*public Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }*/

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


    public static String dateYYYY_MM_DD (int year, int month, int day) {  // yyyy-mm-dd
        String formattedDate = String.format("%d-%02d-%02d",year,month+1,day);
        return formattedDate;
    }

    public static String getTodayYYYY_MM_DD(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date ( );
        String strToday = df.format ( currentTime );
        return strToday;
    }

    public static void hideSoftKeyboard(Context context, EditText editText) {

        keyboardManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(editText != null) {
            Log.i(TAG, "hideSoftKeyboard");
            keyboardManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }


}
