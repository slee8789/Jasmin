package com.study.jasmin.jasmin.rest;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by leesc on 2016-08-10.
 */
public class ReceivedCookieInterceptor implements Interceptor {
    private DalgonaSharedPreferences mDsp;

    public ReceivedCookieInterceptor(Context context){
        mDsp = DalgonaSharedPreferences.getInstanceOf(context);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            mDsp.putHashSet(DalgonaSharedPreferences.KEY_COOKIE, cookies);
        }

        return originalResponse;
    }
}
