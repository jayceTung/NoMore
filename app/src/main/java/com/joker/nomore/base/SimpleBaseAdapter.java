package com.joker.nomore.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker on 2015/10/16.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mData;

    public SimpleBaseAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData == null ? new ArrayList<T>() : new ArrayList<T>(mData);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= mData.size()) {
            return null;
        }
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 添加数据
     * @param list
     */
    public void addAll(List<T> list) {
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 删除指定标记对象
     * @param index 标记
     */
    public void remove(int index) {
        this.mData.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 删除指定对象
     * @param elem
     */
    public void remove(T elem) {
        this.mData.remove(elem);
        notifyDataSetChanged();
    }

    public void replaceAddAll(List<T> list) {
        this.mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 得到资源布局id
     * @return
     */
    public abstract int getItemResource();

    /**
     * 得到子类的视图
     * @return
     */
    public abstract View getItemView(int postision, View view, ViewHolder viewHolder);


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, getItemResource(), null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return getItemView(i, view, holder);
    }

    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<>();
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public <T extends View> T getView(int i) {
            View v = views.get(i);
            if (v == null) {
                v = view.findViewById(i);
                views.put(i, v);
            }
            return (T) v;
        }
    }
}
