package com.joker.nomore.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.joker.nomore.common.Log;

/**
 * Created by Joker on 2015/10/13.
 */
public class VolleyHelper {
    private static final String TAG = "VolleyHelper";

    private RequestQueue queue = null;
    private static volatile VolleyHelper instance = null;

    private VolleyHelper() {
    }

    public static VolleyHelper getInstance() {
        if (instance == null) {
            synchronized(VolleyHelper.class) {
                if (instance == null) {
                    instance = new VolleyHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        queue = Volley.newRequestQueue(context, new OkHttpStack());
        Log.i(TAG, "init");
    }


    public RequestQueue getQueue() {
        if (queue != null) {
            return queue;
        } else {
            throw new IllegalArgumentException("RequestQueue is not initialized.");
        }
    }



}
