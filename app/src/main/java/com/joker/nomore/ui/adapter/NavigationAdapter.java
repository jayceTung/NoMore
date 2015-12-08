package com.joker.nomore.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.nomore.R;
import com.joker.nomore.base.SimpleBaseAdapter;
import com.joker.nomore.bean.NavigationEntity;

import java.util.List;

/**
 * Created by Joker on 2015/10/16.
 */
public class NavigationAdapter extends SimpleBaseAdapter<NavigationEntity> {
    private static final String Tag = "NavigationAdapter";


    public NavigationAdapter(Context mContext, List<NavigationEntity> mData) {
        super(mContext, mData);
    }


    @Override
    public int getItemResource() {
        return R.layout.item_listview_navigation;
    }

    @Override
    public View getItemView(int postision, View view, ViewHolder viewHolder) {
        ImageView imageView = viewHolder.getView(R.id.list_item_navigation_icon);
        TextView textView = viewHolder.getView(R.id.list_item_navigation_name);
        imageView.setImageResource(mData.get(postision).getResId());
        textView.setText(mData.get(postision).getName());
        return view;
    }


}
