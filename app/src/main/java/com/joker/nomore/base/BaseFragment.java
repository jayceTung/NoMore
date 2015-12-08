package com.joker.nomore.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Joker on 2015/10/16.
 */
public abstract class BaseFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {
    protected Context mContext;

    protected static int sPageNum = 0;
    protected int mPreviousTotal = 0;
    protected boolean isLoading = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPageNum = 0;
        sendRequest(sPageNum);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getResId() != 0) {
            View view = inflater.inflate(getResId(), container, false);
            ButterKnife.bind(this, view);
            initView();
            initEvent();
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onRefresh() {
        sPageNum = 0;
        mPreviousTotal = 0;
        sendRequest(sPageNum);
    }

    /**
     * load layout
     * @return R.layout
     */
    protected abstract int getResId();

    /**
     * send request
     * @return GsonRequest
     */
    protected abstract void sendRequest(int sPageNum);

    /**
     * view init
     */
    protected abstract void initView();

    /**
     * Event init
     */
    protected abstract void initEvent();
}
