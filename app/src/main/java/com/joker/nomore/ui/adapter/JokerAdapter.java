package com.joker.nomore.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.joker.nomore.R;
import com.joker.nomore.base.BaseRecyclerAdapter;
import com.joker.nomore.bean.JokeEntity;
import com.joker.nomore.common.Log;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joker on 2015/12/1.
 * 笑话适配器
 *
 */
public class JokerAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {
    private static final String TAG = "JokerAdapter";

    private JokeEntity mJokeEntity;
    private Context mContext;

    public JokerAdapter(Context context) {
        this.mContext = context;
    }

    public JokerAdapter(Context context, JokeEntity jokeEntity) {
        this.mContext = context;
        this.mJokeEntity = jokeEntity;
    }

    public void appendJokes(JokeEntity jokeEntity, int pageNum) {
        if (mJokeEntity != null && pageNum != 0) {
            this.mJokeEntity.getDetail().addAll(jokeEntity.getDetail());
        } else {
            this.mJokeEntity = jokeEntity;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_joke, parent, false);
            return new JokeViewHolder(view);
        } else if (viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_load_footer, parent, false);
            return new FooterViewHolder(view);
        } else if (viewType == NO_PIC) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_joke, parent, false);
            return new JokeTextHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokeViewHolder) {
            Log.i(TAG, "position =" + position);
            Log.i(TAG, "author =" + mJokeEntity.getDetail().get(position).getAuthor());

            ((JokeViewHolder) holder).authorView.setText(mContext.getString(R.string.joke_author) +
                    mJokeEntity.getDetail().get(position).getAuthor().trim());
            ((JokeViewHolder) holder).contentView.setText(mJokeEntity.getDetail().get(position).getContent().trim());
            ((JokeViewHolder) holder).draweeView.setAspectRatio(1f);
            if (TextUtils.isEmpty(mJokeEntity.getDetail().get(position).getPicUrl())) {
//                ((JokeViewHolder) holder).draweeView.setImageURI(Uri.parse("res://" + mContext.getResources().
//                        getResourcePackageName(R.mipmap.image_retry) + "/" + R.mipmap.image_retry));
//                ((JokeViewHolder) holder).draweeView.setVisibility(View.INVISIBLE);
            } else {
                ((JokeViewHolder) holder).draweeView.setImageURI(Uri.parse(mJokeEntity.getDetail().get(position).getPicUrl()));
            }
        } else if (holder instanceof JokeTextHolder) {
            ((JokeTextHolder) holder).authorView.setText(mContext.getString(R.string.joke_author) +
                    mJokeEntity.getDetail().get(position).getAuthor().trim());
            ((JokeTextHolder) holder).contentView.setText(mJokeEntity.getDetail().get(position).getContent().trim());
            ((JokeTextHolder) holder).draweeView.setAspectRatio(1f);
        }

    }

    @Override
    public int getItemCount() {
        return mJokeEntity == null ? 0 : mJokeEntity.getDetail().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mJokeEntity.getDetail().size()) {
            return FOOTER;
        }

        if (TextUtils.isEmpty(mJokeEntity.getDetail().get(position).getPicUrl())) {
            return NO_PIC;
        }
        return NORMAL;
    }

    @Override
    public void destory() {
        mContext = null;
        mJokeEntity = null;
    }

    public static class JokeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_joke_author)TextView authorView;
        @Bind(R.id.item_recycler_joke_content)TextView contentView;
        @Bind(R.id.item_recycler_joke_view)SimpleDraweeView draweeView;

        public JokeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class JokeTextHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_joke_author)TextView authorView;
        @Bind(R.id.item_recycler_joke_content)TextView contentView;
        @Bind(R.id.item_recycler_joke_view)SimpleDraweeView draweeView;

        public JokeTextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            draweeView.setVisibility(View.GONE);
        }
    }

    public static class FooterViewHolder extends  RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
