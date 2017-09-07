package com.joker.nomore;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.common.logging.FLog;
import com.joker.nomore.utils.SimpleUncaughtExceptionHandler;
import com.joker.nomore.utils.VolleyHelper;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;

/**
 * Created by Super on 2017/9/1.
 */

@DefaultLifeCycle(
        application = "com.joker.nomore.WoQuApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class WoQuApplicationLike extends DefaultApplicationLike {
    private static final String TAG = "WoQuApplication";

    public WoQuApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initTinker();
    }

    private void initTinker() {
        if (BuildConfig.TINKER_ENABLE) {
            //开始检查是否有补丁，这里配置的是每隔访问3小时服务器是否有更新。
            TinkerPatch.init(this)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true)
                    .setFetchPatchIntervalByHours(3);

            // 获取当前的补丁版本
            Log.d(TAG, "current patch version is " + TinkerPatch.with().getPatchVersion());

            //每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        }
    }


    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        VolleyHelper.getInstance().init(base);

        //fresco config
        FLog.setMinimumLoggingLevel(FLog.WARN);
//        Fresco.initialize(getApplication(), ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(getApplication()));

        //catch exception
        Thread.setDefaultUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());
    }
}
