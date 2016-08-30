package com.joker.nomore.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Joker on 2015/12/9.
 */
public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        ButterKnife.bind(this);
        initViews();
        initEvent();
    }

    /**
     * go to activity
     * @param clazz
     */
    protected void gotoActivity(Class clazz) {
        Intent intent = getIntent();
        intent.setClass(this, clazz);
        this.startActivity(intent);
    }

    /**
     * go to activity with data
     * @param clazz
     * @param bundle data
     */
    protected void gotoActivity(Class clazz, Bundle bundle) {
        Intent intent = getIntent();
        intent.setClass(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
    }

    /**
     * load layout
     * @return R.layout
     */
    protected abstract int getResId();

    protected abstract void initViews();

    protected abstract void initEvent();


}
