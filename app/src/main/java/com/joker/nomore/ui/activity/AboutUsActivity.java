package com.joker.nomore.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseActivity;
import com.joker.nomore.common.Log;

import butterknife.Bind;

/**
 * Created by Joker on 2015/12/21.
 */
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
    }

    @Override
    protected void initEvent() {
        mTextView.setText(R.string.about_us);
        mNameView.setText(R.string.about_team_name);
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
