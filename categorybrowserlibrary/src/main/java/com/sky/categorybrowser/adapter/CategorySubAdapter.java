package com.sky.categorybrowser.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.R;
import com.sky.categorybrowser.model.Category;
import com.sky.categorybrowser.model.CategorySectionItem;

import java.util.List;

/**
 * 描述：
 *
 * @author renpeng
 * @since 2019/5/28
 */
public abstract class CategorySubAdapter<CategoryMain, SectionHead, SectionItem> extends BaseQuickAdapter<Category<CategoryMain, SectionHead, SectionItem>, BaseViewHolder> {

    public CategorySubAdapter(@Nullable List<Category<CategoryMain, SectionHead, SectionItem>> data) {
        super(R.layout.lib_item_category_sub_rv_item, data);
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(View view) {
        BaseViewHolder baseViewHolder =  super.createBaseViewHolder(view);
        RecyclerView recyclerView = baseViewHolder.getView(R.id.category_sub_rv_item);
        recyclerView.setHasFixedSize(true);
        configSectionRv(recyclerView);
        return baseViewHolder;
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        Log.d("category","section adapter onViewRecycled " + holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Category<CategoryMain, SectionHead, SectionItem> category) {
//        int pos = baseViewHolder.getAdapterPosition();
//        baseViewHolder.itemView.setBackgroundColor(pos % 2 == 0 ? Color.BLUE : Color.GREEN);

        int position = baseViewHolder.getAdapterPosition();

        Log.e("category","section adapter convert " + position);
        RecyclerView recyclerView = baseViewHolder.getView(R.id.category_sub_rv_item);
        final CategorySectionAdapter<SectionHead, SectionItem> sectionAdapter = new CategorySectionAdapter<SectionHead, SectionItem>(getSectionHeadLayoutRes(), category.getCategorySectionList()) {

            @Override
            protected void convertHead(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionHead) {
                convertSectionHead(baseViewHolder, sectionHead);
            }

            @Override
            protected void convert(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionItem) {
                convertSectionItem(baseViewHolder, sectionItem);
            }
        };

        configSectionAdapter(sectionAdapter,position);
        recyclerView.setAdapter(sectionAdapter);

    }

    public abstract void configSectionRv(RecyclerView recyclerView);

    public abstract int getSectionHeadLayoutRes();

    public abstract void convertSectionItem(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionItem);

    public abstract void convertSectionHead(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionHead);

    public abstract void configSectionAdapter(CategorySectionAdapter<SectionHead, SectionItem> sectionAdapter, int position);
}
