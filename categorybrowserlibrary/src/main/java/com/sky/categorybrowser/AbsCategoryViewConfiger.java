package com.sky.categorybrowser;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.adapter.CategorySectionAdapter;
import com.sky.categorybrowser.model.CategorySectionItem;
import com.sky.categorybrowser.ui.GridSectionMultiAvgGapItemDecoration;

/**
 * 描述：默认配置了一些基本样式，子类可直接继承和重写相应方法
 *
 * @author renpeng
 * @since 2019/6/17
 */
public abstract class AbsCategoryViewConfiger<CategoryMain,SectionHead,SectionItem> implements CategoryViewConfiger<CategoryMain,SectionHead,SectionItem> {

    @Override
    public int onGetMainCategoryItemLayoutRes() {
        return R.layout.lib_item_category_main;
    }

    @Override
    public int onGetSectionHeadLayoutRes() {
        return R.layout.lib_item_category_sub_section_head;
    }

    @Override
    public void convertSectionHead(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionHead) {
        baseViewHolder.setText(R.id.category_sub_head_title_tv, sectionHead.header);
    }

    @Override
    public void onConfigSectionAdapter(CategorySectionAdapter<SectionHead, SectionItem> sectionAdapter, int position) {
        sectionAdapter.addItemTypeDef(CategorySectionItem.ITEM_TYPE_TEXT, R.layout.lib_item_category_sub_item_text);
        sectionAdapter.addItemTypeDef(CategorySectionItem.ITEM_TYPE_IMG_TEXT, R.layout.lib_item_category_sub_item_img_text);
    }

    @Override
    public void onConfigSectionRv(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridSectionMultiAvgGapItemDecoration decoration = new GridSectionMultiAvgGapItemDecoration();
        GridSectionMultiAvgGapItemDecoration.SectionDecoration sectionDecoration = new GridSectionMultiAvgGapItemDecoration.SectionDecoration(8, 5, 16, 0,8);
        decoration.addSectionDecoration(CategorySectionItem.ITEM_TYPE_TEXT, sectionDecoration);
        sectionDecoration = new GridSectionMultiAvgGapItemDecoration.SectionDecoration(12, 8, 16, 0, 8);
        decoration.addSectionDecoration(CategorySectionItem.ITEM_TYPE_IMG_TEXT, sectionDecoration);
        decoration.setLastSectionBottomMarginDp(40);
        recyclerView.addItemDecoration(decoration);
    }

}
