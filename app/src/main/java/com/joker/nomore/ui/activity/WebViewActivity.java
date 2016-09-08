package com.joker.nomore.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseActivity;
import com.joker.nomore.common.ConfigConstants;
import com.joker.nomore.common.Log;
import com.joker.nomore.utils.NetWorkUtil;

import butterknife.Bind;

/**
 * Created by Joker on 2015/12/9.
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";

    @Bind(R.id.webView_progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.webView_webView)
    WebView mWebView;
    @Bind(R.id.layoutBar_title)
    TextView mTextView;
    @Bind(R.id.layoutBar_button)
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "url =" + getIntent().getStringExtra(ConfigConstants.INTENT_URL));
    }

    @Override
    protected int getResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {
        mButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEvent() {
        mWebView.setWebChromeClient(new WebViewChrome());
        mWebView.setWebViewClient(new OverWebView());
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                if (!TextUtils.isEmpty(s) && s.startsWith("http://")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
                }
            }
        });
        mWebView.loadUrl(getIntent().getStringExtra(ConfigConstants.INTENT_URL));
        mTextView.setText(getIntent().getStringExtra(ConfigConstants.INTENT_TITLE));
        mButton.setBackgroundResource(R.mipmap.ic_retry);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });

        webViewSetting();
    }

    private void webViewSetting() {
        WebSettings settings = mWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);//支持JS
        settings.setSupportZoom(true);//支持缩放
        /** 有网的情况有缓存， 没有网络则用缓存 **/
        if (NetWorkUtil.isNetworkConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

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

    private class OverWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
