package com.joker.nomore.utils;

import com.joker.nomore.BuildConfig;

/**
 * Created by Super on 2017/9/4.
 */

public class BuildInfo {

    /**
     * they are not final, so they won't change with the BuildConfig values!
     */
    public static boolean DEBUG        = BuildConfig.DEBUG;
    public static String  VERSION_NAME = BuildConfig.VERSION_NAME;
    public static int     VERSION_CODE = BuildConfig.VERSION_CODE;

    public static String MESSAGE       = BuildConfig.MESSAGE;
    public static String PLATFORM      = BuildConfig.PLATFORM;
}
