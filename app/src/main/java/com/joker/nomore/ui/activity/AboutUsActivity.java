package com.joker.nomore.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.joker.nomore.R;
import com.joker.nomore.base.BaseActivity;
import com.joker.nomore.common.Log;
import com.joker.nomore.utils.BaseBuildInfo;
import com.joker.nomore.utils.BuildInfo;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import butterknife.Bind;

/**
 * Created by Joker on 2015/12/21.
 */
@Route(path= "/about/us")
public class AboutUsActivity extends BaseActivity {
    private static final String TAG = "AboutUsActivity";

    @Bind(R.id.about_us_name)
    TextView mNameView;
    @Bind(R.id.layoutBar_title)
    TextView mTextView;
    @Bind(R.id.layoutBar_button)
    Button mButton;

    @Override
    protected int getResId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews() {
        Log.i(TAG, "initViews");
        mButton.setVisibility(View.GONE);

        final StringBuilder sb = new StringBuilder();
        Tinker tinker = Tinker.with(getApplicationContext());
        if (tinker.isTinkerLoaded()) {
            sb.append(String.format("[patch is loaded] \n"));
            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
            sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

        } else {
            sb.append(String.format("[patch is not loaded] \n"));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
        }
        sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));

        mNameView.setText(sb);
    }

    @Override
    protected void initEvent() {
        mTextView.setText(R.string.about_us);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
