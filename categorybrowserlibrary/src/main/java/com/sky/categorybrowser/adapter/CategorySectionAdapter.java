package com.sky.categorybrowser.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.model.CategorySectionItem;

import java.util.List;

/**
 * 描述：
 *
 * @author renpeng
 * @since 2019/5/27
 */
public abstract class CategorySectionAdapter<SectionHead,SectionItem> extends BaseSectionMultiItemQuickAdapter<CategorySectionItem<SectionHead,SectionItem>, BaseViewHolder> {



    public CategorySectionAdapter(int layoutResId, @Nullable List<CategorySectionItem<SectionHead,SectionItem>> data) {
        super(layoutResId, data);
    }

    public void addItemTypeDef(int type,int layoutRes){
        addItemType(type,layoutRes);
    }

}
