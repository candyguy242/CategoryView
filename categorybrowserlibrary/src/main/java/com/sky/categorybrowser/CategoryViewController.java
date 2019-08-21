package com.sky.categorybrowser;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.adapter.CategoryMainAdapter;
import com.sky.categorybrowser.adapter.CategorySectionAdapter;
import com.sky.categorybrowser.adapter.CategorySubAdapter;
import com.sky.categorybrowser.model.Category;
import com.sky.categorybrowser.model.CategorySectionItem;
import com.sky.categorybrowser.ui.CategoryPagerSnapHelper;

import java.util.List;

/**
 * 描述：{@link CategoryBrowserView}的默认配置器实现，三级目录结构，主目录展示为纵向列表，子目录在右侧展示为标题+网格，支持viewpager式浏览<br>
 *
 * @author renpeng
 * @since 2019/5/28
 */
class CategoryViewController<CategoryMain, SectionHead, SectionItem> {

    private CategoryBrowserView categoryBrowserView;
    private CategoryMainAdapter<CategoryMain, SectionHead, SectionItem> categoryMainAdapter;
    private CategorySubAdapter<CategoryMain, SectionHead, SectionItem> categorySubAdapter;
    private final CategoryPagerSnapHelper categoryPagerSnapHelper;
    private int mSelectedPageIndex = 0;
    private CategoryViewConfiger mViewConfiger;

    public CategoryViewController(CategoryBrowserView categoryBrowserView, CategoryViewConfiger viewConfiger) {
        this.categoryBrowserView = categoryBrowserView;
        this.mViewConfiger = viewConfiger;
        if(viewConfiger == null){
            throw new RuntimeException("view configer can't be null");
        }
        categoryPagerSnapHelper = new CategoryPagerSnapHelper() {
            @Override
            public void onPageChange(int newPos, int currentPos) {
                if (categoryMainAdapter != null) {
                    setMainAdapterPos(newPos);
                }
                mSelectedPageIndex = newPos;
            }

            @Override
            public void onDragToPrePage(RecyclerView recyclerView, int currentPos) {
                super.onDragToPrePage(recyclerView, currentPos);
                //复位到底端
                int count = recyclerView.getChildCount();
                View itemView = null;
                if (count > 1 && (itemView = recyclerView.getChildAt(0)) != null) {
                    RecyclerView itemRv = itemView.findViewById(R.id.category_sub_rv_item);
                    if (itemRv.getAdapter() != null) {
                        int toPosition = itemRv.getAdapter().getItemCount() - 1;
                        itemRv.scrollToPosition(toPosition);
//                        Log.d("category", "itemRv scroll to position=" + toPosition);
                    }
                }
            }

            @Override
            public void onDragToNextPage(RecyclerView recyclerView, int currentPos) {
                super.onDragToNextPage(recyclerView, currentPos);
                //复位到顶端
                int count = recyclerView.getChildCount();
                View itemView = null;
                if (count > 1 && (itemView = recyclerView.getChildAt(1)) != null) {
                    RecyclerView itemRv = itemView.findViewById(R.id.category_sub_rv_item);
                    itemRv.scrollToPosition(0);
                }
            }
        };
        categoryPagerSnapHelper.attachToRecyclerView(categoryBrowserView.getSubCategoryRv());
    }

    private void setMainAdapterPos(int newPos) {
        //如果可以的话，自动多露出两个item
        int curPos = categoryMainAdapter.getSelectedPos();
        categoryMainAdapter.setSelectedPos(newPos);
        categoryMainAdapter.notifyDataSetChanged();
        int scrollPos = newPos;
        int extra = 2;
        if (newPos > curPos) {
            int totalPos = categoryMainAdapter.getItemCount() - 1;
            for (int add = 1; add <= extra; add++) {
                int testPos = newPos + add;
                if (testPos <= totalPos) {
                    scrollPos = testPos;
                } else {
                    break;
                }
            }
        } else {
            for (int add = -1; add >= -extra; add--) {
                int testPos = newPos + add;
                if (testPos >= 0) {
                    scrollPos = testPos;
                } else {
                    break;
                }
            }
        }
        categoryBrowserView.getMainCategoryRv().scrollToPosition(scrollPos);
    }

    public int getSelectedCategoryIndex() {
        return mSelectedPageIndex;
    }

    public final void setCategoryList(List<Category<CategoryMain, SectionHead, SectionItem>> categoryList, int selected) {
        mSelectedPageIndex = selected;
        categoryMainAdapter = new CategoryMainAdapter<CategoryMain, SectionHead, SectionItem>(mViewConfiger.onGetMainCategoryItemLayoutRes(), categoryList) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, Category<CategoryMain, SectionHead, SectionItem> categoryMain) {
                mViewConfiger.convertMainCategoryItem(baseViewHolder, categoryMain);
                baseViewHolder.itemView.setSelected(getSelectedPos() == baseViewHolder.getAdapterPosition());
            }
        };
        categoryMainAdapter.setSelectedPos(selected);
        //Important!!!!
        categoryPagerSnapHelper.setCurrentPagePos(selected);

        categoryBrowserView.getMainCategoryRv().setAdapter(categoryMainAdapter);
        categorySubAdapter = new CategorySubAdapter<CategoryMain, SectionHead, SectionItem>(categoryList) {
            @Override
            public void configSectionRv(RecyclerView recyclerView) {
                mViewConfiger.onConfigSectionRv(recyclerView);
            }

            @Override
            public int getSectionHeadLayoutRes() {
                return mViewConfiger.onGetSectionHeadLayoutRes();
            }

            @Override
            public void convertSectionItem(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionItem) {
                mViewConfiger.convertSectionItem(baseViewHolder, sectionItem);
            }

            @Override
            public void convertSectionHead(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionHead) {
                mViewConfiger.convertSectionHead(baseViewHolder, sectionHead);
            }

            @Override
            public void configSectionAdapter(CategorySectionAdapter<SectionHead, SectionItem> sectionAdapter, int position) {
                mViewConfiger.onConfigSectionAdapter(sectionAdapter, position);
            }
        };
        categoryBrowserView.getSubCategoryRv().setAdapter(categorySubAdapter);
        categoryBrowserView.getSubCategoryRv().setRecycledViewPool(new RecyclerView.RecycledViewPool());
        categoryBrowserView.getSubCategoryRv().scrollToPosition(selected);

        categoryMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (categoryMainAdapter.getSelectedPos() != i) {
                    setMainAdapterPos(i);
                    categoryBrowserView.getSubCategoryRv().scrollToPosition(i);

                    //子列表复位到顶部
                    RecyclerView.LayoutManager layoutManager = categoryBrowserView.getSubCategoryRv().getLayoutManager();
                    int count = layoutManager.getChildCount();
                    if (count > 0) {
                        View sectionView = layoutManager.getChildAt(0);
                        int adapterPos = categoryBrowserView.getSubCategoryRv().getChildAdapterPosition(sectionView);
                        RecyclerView sectionNestRv = sectionView.findViewById(R.id.category_sub_rv_item);
                        sectionNestRv.scrollToPosition(0);
                        Log.d("category", "main click section rv child count=" + count + ",pos=" + adapterPos);
                    }
                }
            }
        });
    }

}
