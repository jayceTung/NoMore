package com.joker.nomore;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.joker.nomore.base.ImagePipelineConfigFactory;
import com.joker.nomore.utils.SimpleUncaughtExceptionHandler;
import com.joker.nomore.utils.VolleyHelper;

/**
 * Created by Joker on 2015/10/16.
 */
public class NoMoreApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);

        //fresco config
        FLog.setMinimumLoggingLevel(FLog.WARN);
        Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));

        //catch exception
        Thread.setDefaultUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
