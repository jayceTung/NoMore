package com.joker.nomore.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by Joker on 2015/12/4.
 */
public class RecycleRefreshLayout extends SwipeRefreshLayout {
    public RecycleRefreshLayout(Context context) {
        super(context);
    }

    public RecycleRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        return canChildScrollUp(this);
    }

    public boolean canChildScrollUp(ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            View temp = v.getChildAt(i);
            if (temp instanceof RecyclerView) {
                if (canRecycleViewScroll((RecyclerView) temp)) return true;
            } else if (temp instanceof AbsListView){
                if (ViewCompat.canScrollVertically(temp, -1))return true;
            }else if (temp instanceof ViewGroup) {
                if (canChildScrollUp((ViewGroup) temp)) return true;
            } else if (ViewCompat.canScrollVertically(temp, -1)) return true;
        }
        return false;
    }

    public boolean canRecycleViewScroll(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
            return linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0;
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            return gridLayoutManager.findFirstCompletelyVisibleItemPosition() != 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            return ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(lastPositions)[0] != 0;
        }
        return false;
    }

}
