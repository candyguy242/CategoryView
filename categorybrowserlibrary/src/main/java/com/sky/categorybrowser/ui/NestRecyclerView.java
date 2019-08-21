package com.sky.categorybrowser.ui;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class NestRecyclerView extends RecyclerView {

    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private float mLastY = 0;// 记录上次Y位置
    private float mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    public NestRecyclerView(Context context) {
        this(context, null);
    }

    public NestRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean interceptTouchEvent = super.onInterceptTouchEvent(event);
        if(!interceptTouchEvent){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastY = event.getY();
                    //不允许父View拦截事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    interceptTouchEvent = event.getY() - mLastY > mTouchSlop;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
        }
//        Log.d("nest", "onInterceptTouchEvent=" + event.getEventTime() + ",result=" + interceptTouchEvent);
        return interceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                //不允许父View拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = event.getY();
                boolean disallowIntercept = isIntercept(nowY);
                getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
                mLastY = nowY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isIntercept(float nowY) {

        boolean disallowIntercept = true;

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        //得到RecyclerView对应所有数据的大小
        int totalItemCount = layoutManager.getItemCount();
        if (totalItemCount > 0) {
            if (layoutManager instanceof GridLayoutManager) {
                //得到当前界面，最后一个子视图对应的position
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                //得到当前界面，第一个子视图的position
                firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            }
            if (nowY < mLastY) {
                //手指向上滑动，判断是否触底
                if (lastVisibleItemPosition == totalItemCount - 1 && !NestRecyclerView.this.canScrollVertically(1)) {
                    //最后视图对应的position等于总数-1时，说明上一次滑动结束时，触底了
                    disallowIntercept = false;
                }
            } else {
                //向下滑动，判断是否触顶
                if (firstVisibleItemPosition == 0 && !NestRecyclerView.this.canScrollVertically(-1)) {
                    //第一个视图的position等于0，说明上一次滑动结束时，触顶了
                    disallowIntercept = false;
                }
            }
        } else {
            disallowIntercept = false;
        }
//        Log.d("nest", "disallowintercept=" + disallowIntercept);
        return disallowIntercept;
    }
}