package com.joker.nomore.common;

import com.joker.nomore.BuildConfig;

/**
 * Created by Joker on 2015/10/16.
 * Logcat class
 */
public class Log {
    private static final boolean debug = BuildConfig.DEBUG;

    public static void i(String tag, String str) {
        if (debug) {
            android.util.Log.i(tag, str);
        }
    }

    public static void e(String tag, String str) {
        if (debug) {
            android.util.Log.e(tag, str);
        }
    }

    public static void v(String tag, String str) {
        if (debug) {
            android.util.Log.v(tag, str);
        }
    }

    public static void d(String tag, String str) {
        if (debug) {
            android.util.Log.d(tag, str);
        }
    }

    public static void w(String tag, String str) {
        if (debug) {
            android.util.Log.w(tag, str);
        }
    }
}
