package com.joker.nomore.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseRecyclerAdapter;
import com.joker.nomore.bean.NewsEntity;
import com.joker.nomore.common.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joker on 2015/12/7.
 */
public class NewsAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private static final String TAG = "NewsAdapter";

    private NewsEntity mNewsEntity;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat;
    private Date mDate;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public NewsAdapter(Context context, NewsEntity newsEntity) {
        this.mContext = context;
        this.mNewsEntity = newsEntity;
    }

    public void appendJokes(NewsEntity newsEntity, int pageNum) {
        if (mNewsEntity != null && pageNum != 0) {
            this.mNewsEntity.getDetail().addAll(newsEntity.getDetail());
        } else {
            this.mNewsEntity = newsEntity;
            this.mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.mDate = new Date();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_news, parent, false);
            return new NewsViewHolder(view);
        } else if (viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_load_footer, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            Log.i(TAG, "position =" + position);
            Log.i(TAG, "url =" + mNewsEntity.getDetail().get(position).getArticle_url());
            ((NewsViewHolder) holder).fromView.setText(mContext.getString(R.string.joke_author) + mNewsEntity.getDetail().get(position).getSource());
            mDate.setTime(Long.valueOf(mNewsEntity.getDetail().get(position).getBehot_time()));
            ((NewsViewHolder) holder).timeView.setText(mSimpleDateFormat.format(mDate));
            ((NewsViewHolder) holder).titleView.setText(mNewsEntity.getDetail().get(position).getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return mNewsEntity == null ? 0 : mNewsEntity.getDetail().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mNewsEntity.getDetail().size()) {
            return FOOTER;
        }
        return NORMAL;
    }

    @Override
    public void destory() {
        mContext = null;
        mNewsEntity = null;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_news_from)TextView fromView;
        @Bind(R.id.item_recycler_news_time)TextView timeView;
        @Bind(R.id.item_recycler_news_title)TextView titleView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class FooterViewHolder extends  RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
