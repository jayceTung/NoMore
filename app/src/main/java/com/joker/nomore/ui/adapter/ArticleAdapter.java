package com.joker.nomore.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseRecyclerAdapter;
import com.joker.nomore.bean.ArticleEntity;
import com.joker.nomore.common.ConfigConstants;
import com.joker.nomore.common.Log;
import com.joker.nomore.ui.activity.WebViewActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joker on 2015/12/10.
 */
public class ArticleAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ArticleAdapter";

    private ArticleEntity mArticleEntity;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat;
    private Date mDate;
    private Intent mIntent;

    public ArticleAdapter(Context context) {
        this.mContext = context;
    }

    public ArticleAdapter(Context context, ArticleEntity articleEntity) {
        this.mContext = context;
        this.mArticleEntity = articleEntity;
    }

    public void appendArticle(ArticleEntity articleEntity, int pageNum) {
        if (mArticleEntity != null && pageNum != 0) {
            this.mArticleEntity.getDetail().addAll(articleEntity.getDetail());
        } else {
            this.mArticleEntity = articleEntity;
            this.mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.mDate = new Date();
            mIntent = new Intent(mContext, WebViewActivity.class);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "viewType =" + viewType);
        if (viewType == NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_article, parent, false);
            return new ArticleViewHold(view);
        } else if (viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_load_footer, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleViewHold) {
            Log.i(TAG, "position =" + position);
            Log.i(TAG, "url =" + mArticleEntity.getDetail().get(position).getArticle_url());
            ((ArticleViewHold) holder).titleView.setText(mArticleEntity.getDetail().get(position).getTitle());
            ((ArticleViewHold) holder).contentView.setText(Html.fromHtml(mArticleEntity.getDetail().get(position).getMy_abstract()));
        }

    }

    @Override
    public int getItemCount() {
        return mArticleEntity == null ? 0 : mArticleEntity.getDetail().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mArticleEntity.getDetail().size()) {
            return FOOTER;
        }
        return NORMAL;
    }

    @Override
    public void destroy() {
        mContext = null;
        mArticleEntity = null;
    }

    public class ArticleViewHold extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_article_title)
        TextView titleView;
        @Bind(R.id.item_recycler_article_content)
        TextView contentView;

        public ArticleViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initEvent(itemView);

        }

        private void initEvent(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIntent.putExtra(ConfigConstants.INTENT_URL, mArticleEntity.getDetail().get(getPosition()).getArticle_url());
                    mIntent.putExtra(ConfigConstants.INTENT_TITLE, mArticleEntity.getDetail().get(getPosition()).getTitle());
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
