package com.joker.nomore.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Joker on 2015/12/4.
 */
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    protected static final int NORMAL = 0;
    protected static final int HEAD = 1;
    protected static final int FOOTER = 2;
    protected static final int NO_PIC = 3;

    /** desory resource */
    public abstract void destory();
}
