package com.sky.categorybrowser;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.adapter.CategorySectionAdapter;
import com.sky.categorybrowser.model.Category;
import com.sky.categorybrowser.model.CategorySectionItem;
import com.sky.categorybrowser.ui.GridSectionMultiAvgGapItemDecoration;

import java.util.List;

/**
 * 描述：配置器接口<br>
 * 在{@link #onConfigSectionRv(RecyclerView)}中可以对右侧列表进行配置，比如可以使用{@link GridSectionMultiAvgGapItemDecoration}来添加间距<br>
 * 子类通常只需要继承并重写接口中相应的方法即可<br>
 * 添加目录section多type布局可以在{@link CategoryViewConfiger#onConfigSectionAdapter(CategorySectionAdapter, int)}中进行<br>
 * 添加目录子项点击事件可以在{@link #convertSectionItem(BaseViewHolder, CategorySectionItem)}中进行操作<br>
 *
 * @author renpeng
 * @since 2019/5/28
 */
public interface CategoryViewConfiger<CategoryMain,SectionHead,SectionItem> {


    /**
     * 设置主目录列表条目的Layout资源id
     */
    int onGetMainCategoryItemLayoutRes();

    /**
     * 设置主目录数据展示效果
     * @param helper
     * @param item 要展示的主目录数据
     */
    void convertMainCategoryItem(BaseViewHolder helper, Category<CategoryMain,SectionHead,SectionItem> item);

    /**
     * 获取子目录列表header项的layout资源id
     * @return
     */
    int onGetSectionHeadLayoutRes();

    /**
     * 设置子目录列表header项的展示效果
     * @param baseViewHolder
     * @param sectionHead 要展示的子列表head项
     */
    void convertSectionHead(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionHead);

    /**
     * 设置子目录列表item项的展示效果
     * @param baseViewHolder
     * @param sectionItem 要展示的子列表item项
     */
    void convertSectionItem(BaseViewHolder baseViewHolder, CategorySectionItem<SectionHead, SectionItem> sectionItem);

    /**
     * 配置子列表所使用recyclerView
     * @param recyclerView
     */
    void onConfigSectionRv(RecyclerView recyclerView);

    /**
     * 配置子列表所使用的adapter，可以添加multiItem所需要对应的layout文件
     * @param sectionAdapter
     * @param position
     */
    void onConfigSectionAdapter(CategorySectionAdapter<SectionHead, SectionItem> sectionAdapter, int position);
}
