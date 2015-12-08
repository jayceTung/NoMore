package com.joker.nomore.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.joker.nomore.R;
import com.joker.nomore.base.BaseFragment;
import com.joker.nomore.bean.NavigationEntity;
import com.joker.nomore.common.Log;
import com.joker.nomore.presenter.Ipresenter;
import com.joker.nomore.presenter.presenterImpl.MainPresenter;
import com.joker.nomore.ui.adapter.NavigationAdapter;
import com.joker.nomore.ui.fragment.JokesFragment;
import com.joker.nomore.ui.fragment.NewsFragment;
import com.joker.nomore.view.MainView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements MainView, AdapterView.OnItemClickListener{
    private static final String TAG = "MainActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.main_navigation_list)
    ListView mListView;

    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationAdapter mNaviAdapter;
    private Ipresenter mPresenter;
    private int mCurPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        mPresenter = new MainPresenter(this, this);
        mPresenter.initialized();
        switchItem(0);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /* findView */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        initSystemBar(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_search:
                break;
            case R.id.action_about_us:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.color_status_color);

    }


    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    public void initializedView(List<BaseFragment> fragments, List<NavigationEntity> navigationList) {
        Log.i(TAG, "init");
        mNaviAdapter = new NavigationAdapter(this,navigationList);
        mListView.setAdapter(mNaviAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mCurPosition = i;
        mNaviAdapter.notifyDataSetChanged();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Log.i(TAG, "onItemClick position =" + i);
        Log.i(TAG, "Title =" + ((NavigationEntity) mNaviAdapter.getItem(mCurPosition)).getName());
        mToolbar.setTitle(((NavigationEntity) mNaviAdapter.getItem(mCurPosition)).getName());
        switchItem(i);
    }

    private void switchItem(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new JokesFragment();
                break;
            case 1:
                fragment = new NewsFragment();
                break;
            default:
                fragment = new JokesFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.pager, fragment).commit();
    }
}
