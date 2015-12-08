package com.joker.nomore;

import android.app.Application;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.joker.nomore.base.ImagePipelineConfigFactory;
import com.joker.nomore.utils.VolleyHelper;

/**
 * Created by Joker on 2015/10/16.
 */
public class NoMoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);

        //fresco config
        FLog.setMinimumLoggingLevel(FLog.WARN);
        Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));

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
