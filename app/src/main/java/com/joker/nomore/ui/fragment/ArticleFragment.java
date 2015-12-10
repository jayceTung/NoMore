package com.joker.nomore.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.joker.nomore.R;
import com.joker.nomore.api.ApiContants;
import com.joker.nomore.base.BaseFragment;
import com.joker.nomore.bean.ArticleEntity;
import com.joker.nomore.common.Log;
import com.joker.nomore.ui.adapter.ArticleAdapter;
import com.joker.nomore.utils.GsonRequest;
import com.joker.nomore.utils.ToastUtil;
import com.joker.nomore.utils.VolleyHelper;
import com.joker.nomore.view.RecycleRefreshLayout;

import butterknife.Bind;

/**
 * Created by Joker on 2015/12/10.
 */
public class ArticleFragment extends BaseFragment {
    private static final String TAG = "ArticleFragment";

    @Bind(R.id.jokes_refresh)
    RecycleRefreshLayout mRefresh;
    @Bind(R.id.jokes_recycler)
    RecyclerView mRecyclerView;

    private GsonRequest<ArticleEntity> mGsonRequest;
    private ArticleAdapter mArticleAdapter;


    @Override
    protected int getResId() {
        return R.layout.fragment_jokes;
    }

    @Override
    protected void sendRequest(int pageNum) {
        String url = ApiContants.Uri.host + ApiContants.Path.articles + ApiContants.append.page + sPageNum;
        Log.i(TAG, "url =" + url);
        mGsonRequest = new GsonRequest<ArticleEntity>(url, ArticleEntity.class, new Response.Listener<ArticleEntity>() {
            @Override
            public void onResponse(ArticleEntity response) {
                if (response != null && response.getStatus().equals(ApiContants.StatusCode.success)) {
                    Log.i(TAG, "JokeEntity =" + response.toString());
                    Log.i(TAG, "pageNum =" + sPageNum);
                    mArticleAdapter.appendArticle(response, sPageNum);
                    mArticleAdapter.notifyDataSetChanged();
                    if (mRefresh.isRefreshing()) {
                        mRefresh.setRefreshing(false);
                        sPageNum++;
                    } else {
                        sPageNum++;
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showToast(getActivity(), R.string.loading_failed);
            }
        });

        VolleyHelper.getInstance().getQueue().add(mGsonRequest);
    }

    @Override
    protected void initView() {
        mRefresh.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.gplus_color_1),
                ContextCompat.getColor(getActivity(), R.color.gplus_color_2),
                ContextCompat.getColor(getActivity(), R.color.gplus_color_3),
                ContextCompat.getColor(getActivity(), R.color.gplus_color_4)
        );
        mRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mArticleAdapter = new ArticleAdapter(getContext());
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    @Override
    protected void initEvent() {
        mRefresh.setOnRefreshListener(this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = 0;
                int firstVisibleItem = 0;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
                    //通过LayoutManager找到当前显示的最后的item的position
                    lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    firstVisibleItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
                    //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                    //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                    int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItem = findMax(lastPositions);
                    firstVisibleItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
                }

                //判断加载完成了...
                if (isLoading) {
                    if (totalItemCount > mPreviousTotal) {
                        isLoading = false;
                        mPreviousTotal = totalItemCount;
                        Log.i(TAG, "no loading");
                    }
                }
                //totalItemCount > visibleItemCount 超过一个页面才有加载更多
                if (!isLoading && totalItemCount > mPreviousTotal && totalItemCount > visibleItemCount && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 1)) {
                    Log.i(TAG, "loadingFresh");
                    loadingFresh();
                    isLoading = true;
                }

            }
        });
    }

    //找到数组中的最大值

    private int findMax(int[] lastPositions) {

        int max = lastPositions[0];
        for (int value : lastPositions) {
            //       int max    = Math.max(lastPositions,value);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    /**
     * 加载更多
     */
    private void loadingFresh() {
        sendRequest(sPageNum);
    }

    /**
     * scroll to view top
     */
    private void scrolltoTop() {
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mArticleAdapter != null) {
            mArticleAdapter.destroy();
        } else {
            mArticleAdapter = null;
        }
        mGsonRequest = null;
    }
}
