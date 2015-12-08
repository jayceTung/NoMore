package com.joker.nomore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by Joker on 2015/12/8.
 */
public class ProgressWebView extends WebView {
    private ProgressBar progressBar;
    private static final int LOADED = 100;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 3, 0, 0));
        addView(progressBar);

        setWebChromeClient(new ChromeClient());
    }



    public class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == LOADED) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() == GONE) {
                    progressBar.setVisibility(VISIBLE);
                }
                progressBar.setProgress(newProgress);
            }


            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams params = (LayoutParams) progressBar.getLayoutParams();
        params.x = l;
        params.y = t;
        progressBar.setLayoutParams(params);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
