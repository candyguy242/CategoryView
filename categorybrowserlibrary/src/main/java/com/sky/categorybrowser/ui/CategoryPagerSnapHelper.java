package com.sky.categorybrowser.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 描述：
 *
 * @author renpeng
 * @since 2019/5/30
 */
public class CategoryPagerSnapHelper extends PagerSnapHelper {

    private int currentPagePos = -1;
    private RecyclerView mRecyclerView;
    private int totalDy = 0;
    private Runnable mAdjustPageRunnable = new Runnable() {
        @Override
        public void run() {
            if(mRecyclerView != null && mRecyclerView.getLayoutManager() instanceof LinearLayoutManager){
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int page = layoutManager.findFirstVisibleItemPosition();
                if(page != currentPagePos){
//                    Log.e("category", String.format("adjust page task is running!! page=%d,curpage=%d", page, currentPagePos));
                    onPageChange(page,currentPagePos);
                    currentPagePos = page;
                }
            }
        }
    };

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        //java.lang.IllegalStateException: An instance of OnFlingListener already set.
        if (mRecyclerView == recyclerView) {
            return;
        }
        super.attachToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        if (mRecyclerView != null) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        Log.w("category", String.format("onScrollStateChanged!! total=%d,curpage=%d", totalDy, currentPagePos));
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int currentDy = totalDy;
                    totalDy += dy;
//                    Log.d("category", String.format("section rv onScrolled=%d,%d,child count=%d", dx, dy, recyclerView.getChildCount()));
                    if (totalDy > 0 && currentDy <= 0) {
                        //页面向上滑动，展示下一页
                        onDragToNextPage(recyclerView, currentPagePos);
                    } else if (totalDy < 0 && currentDy >= 0) {
                        //页面向下滑动，展示上一页
                        onDragToPrePage(recyclerView, currentPagePos);
                    } else {
//                        Log.e("category", String.format("scroll detected but no direction is detected, maybe too fast!! total=%d,curpage=%d", totalDy, currentPagePos));
                        mRecyclerView.removeCallbacks(mAdjustPageRunnable);
                        mRecyclerView.postDelayed(mAdjustPageRunnable,300);
                    }
                }
            });
        }
    }

    public void onDragToPrePage(RecyclerView recyclerView, int currentPos) {
        Log.e("category", String.format("section rv onDragToPrePage=%d", currentPos - 1));
    }

    public void onDragToNextPage(RecyclerView recyclerView, int currentPos) {
        Log.e("category", String.format("section rv onDragToNextPage=%d", currentPos + 1));
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        View view = super.findSnapView(layoutManager);
        if (view != null) {
            int newPos = mRecyclerView.getChildAdapterPosition(view);
            if (currentPagePos == -1) {
                currentPagePos = newPos;
            }
            if (newPos >= 0 && newPos != currentPagePos) {
                mRecyclerView.removeCallbacks(mAdjustPageRunnable);
                onPageChange(newPos, currentPagePos);
            }
            currentPagePos = newPos;
            totalDy = 0;
        }
        Log.e("category", String.format("findSnapView curPage=%d", currentPagePos));
        return view;
    }

    public void onPageChange(int newPos, int currentPos) {

    }

    public int getCurrentPagePos() {
        return currentPagePos;
    }

    public void setCurrentPagePos(int currentPagePos) {
        this.currentPagePos = currentPagePos;
    }
}
