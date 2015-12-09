package com.joker.nomore.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseActivity;
import com.joker.nomore.common.ConfigConstants;
import com.joker.nomore.common.Log;

/**
 * Created by Joker on 2015/12/9.
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private ViewFlipper mViewFlipper;
    private TextView mTextView;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "url =" + getIntent().getStringExtra(ConfigConstants.INTENT_URL));
    }

    @Override
    protected void onLeftFling() {
        mViewFlipper.setInAnimation(this, R.animator.in_rightleft);
        mViewFlipper.setOutAnimation(this, R.animator.out_rightleft);
    }

    @Override
    protected void onRightFling() {
        mViewFlipper.setInAnimation(this, R.animator.in_leftright);
        mViewFlipper.setOutAnimation(this, R.animator.out_leftright);

    }

    @Override
    protected void onTopFling() {

    }

    @Override
    protected void onDownFling() {

    }

    @Override
    protected int getResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.webView_flipper);
        mProgressBar = (ProgressBar) this.findViewById(R.id.webView_progressBar);
        mWebView = (WebView) this.findViewById(R.id.webView_webView);
        mTextView = (TextView) this.findViewById(R.id.layoutBar_title);
        mButton = (Button) this.findViewById(R.id.layoutBar_button);
    }

    @Override
    protected void initEvent() {
        mWebView.setWebChromeClient(new WebViewChrome());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                if (!TextUtils.isEmpty(s) && s.startsWith("http://")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
                }
            }
        });
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mDetector.onTouchEvent(motionEvent);
            }
        });
        mWebView.loadUrl(getIntent().getStringExtra(ConfigConstants.INTENT_URL));
        mTextView.setText(getIntent().getStringExtra(ConfigConstants.INTENT_TITLE));
    }

    private class WebViewChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress >= 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
