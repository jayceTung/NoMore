package com.joker.nomore.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Joker on 2015/10/13.
 */
public class VolleyHelper {
    private RequestQueue queue = null;
    private static  volatile VolleyHelper instance = null;

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
    }


    public RequestQueue getQueue() {
        if (queue != null) {
            return queue;
        } else {
            throw new IllegalArgumentException("RequestQueue is not initialized.");
        }
    }



}
