package com.joker.nomore.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Joker on 2015/12/9.
 */
public abstract class BaseActivity extends Activity
    implements GestureDetector.OnGestureListener{

    /** 滑动的最小距离 */
    private static final int FLING_MIN_DISTANCE = 200;
    /** 滑动的差异方向偏差距离，如当前是上下滑动，则左右滑动的距离大于此值则作废操作 */
    private static final int FLING_DIFFER_DISTANCE = 300;
    /** 滑动的最小速度,像素/秒 */
    private static final int FLING_MIN_VELOCITY = 0;

    protected GestureDetector mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        mDetector = new GestureDetector(this, this);
        initViews();
        initEvent();
    }

    /**
     * go to activity
     * @param clazz
     */
    protected void gotoActivity(Class clazz) {
        this.startActivity(new Intent(this, clazz));
    }

    /**
     * go to activity with data
     * @param clazz
     * @param bundle data
     */
    protected void gotoActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float e1e2X = motionEvent.getX() - motionEvent1.getX();
        float e2e1X = motionEvent1.getX() - motionEvent.getX();
        float e1e2Y = motionEvent.getY() - motionEvent1.getY();
        float e2e1Y = motionEvent1.getY() - motionEvent.getY();

        if (e1e2X > FLING_MIN_DISTANCE && Math.abs(e1e2Y) < FLING_DIFFER_DISTANCE && Math.abs(v) > FLING_MIN_VELOCITY) {
            onLeftFling();
            return true;
        } else if (e2e1X > FLING_MIN_DISTANCE && Math.abs(e2e1Y) < FLING_DIFFER_DISTANCE && Math.abs(v) > FLING_MIN_VELOCITY) {
            // Fling right
            onRightFling();
            return true;
        } else if (e1e2Y > FLING_MIN_DISTANCE && Math.abs(e1e2X) < FLING_DIFFER_DISTANCE && Math.abs(v) > FLING_MIN_VELOCITY) {
            onTopFling();
            return true;
        } else if (e2e1Y > FLING_MIN_DISTANCE && Math.abs(e2e1X) < FLING_DIFFER_DISTANCE && Math.abs(v) > FLING_MIN_VELOCITY) {
            // Fling bottom
            onDownFling();
            return true;
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    /**
     * 左滑手势
     */
    protected abstract void onLeftFling();

    /**
     * 右滑手势
     */
    protected abstract void onRightFling();

    /**
     * 上滑手势
     */
    protected abstract void onTopFling();

    /**
     * 下滑手势
     */
    protected abstract void onDownFling();

    /**
     * load layout
     * @return R.layout
     */
    protected abstract int getResId();

    protected abstract void initViews();

    protected abstract void initEvent();
}
