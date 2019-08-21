package com.sky.categorybrowser.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.model.Category;

import java.util.List;

/**
 * 目录适配器，
 *
 * @author renpeng
 * @since 2019/5/27
 */
public abstract class CategoryMainAdapter<CategoryMain,SectionHead,SectionItem> extends BaseQuickAdapter<Category<CategoryMain,SectionHead,SectionItem>, BaseViewHolder> {

    private int mSelectedPos = 1;

    public CategoryMainAdapter(int layoutResId, @Nullable List<Category<CategoryMain,SectionHead,SectionItem>> data) {
        super(layoutResId, data);
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.mSelectedPos = selectedPos;
    }


}
