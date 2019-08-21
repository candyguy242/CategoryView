package com.sky.categorydemo;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.categorybrowser.AbsCategoryViewConfiger;
import com.sky.categorybrowser.adapter.CategorySectionAdapter;
import com.sky.categorybrowser.model.Category;
import com.sky.categorybrowser.model.CategorySectionItem;
import com.sky.categorydemo.repo.model.CategoryBean;
import com.sky.categorydemo.repo.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 最终Item Section样式和间距可以在此设置，可以查看父类的方法
 */
public class DemoCategoryViewConfiger extends AbsCategoryViewConfiger<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> {

    private Context mContext;

    public DemoCategoryViewConfiger(Context context) {
        this.mContext = context;
    }

    /**
     * 一个数据转换过程，转换成控件所需的结构
     * @param sCategoryBaseList
     */
    public static List<Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem>> convertToCategory(List<CategoryBean.MainCategory> sCategoryBaseList) {
        List<Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem>> categoryList = new ArrayList<>(sCategoryBaseList.size());
        for (CategoryBean.MainCategory categoryBase : sCategoryBaseList) {
            Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> category = new Category<>(categoryBase);
            categoryList.add(category);
            //展平子项
            List<CategorySectionItem<CategoryBean.LevelonesBean, CategoryItem>> categorySectionItemList = new ArrayList<>();
            category.setCategorySectionList(categorySectionItemList);

            for (CategoryBean.LevelonesBean levelOne : categoryBase.getLevelones()) {

                //添加section head
                CategorySectionItem<CategoryBean.LevelonesBean, CategoryItem> head = new CategorySectionItem<>(true, levelOne.getStrName(), levelOne);
                head.sectionHead = levelOne;
                categorySectionItemList.add(head);

                //添加section item
                if (levelOne.getVecCategorys() != null) {
                    CategorySectionItem<CategoryBean.LevelonesBean, CategoryItem> item = null;
                    for (CategoryItem sCategoryChild : levelOne.getVecCategorys()) {
                        item = new CategorySectionItem<>(levelOne, sCategoryChild, levelOne.getShowtype());
                        categorySectionItemList.add(item);
                    }
                }
            }
        }
        return categoryList;
    }


    @Override
    public void onConfigSectionAdapter(CategorySectionAdapter<CategoryBean.LevelonesBean, CategoryItem> sectionAdapter, int position) {
        //重设控件中默认的布局，也可以增加itemView类型定义
        sectionAdapter.addItemTypeDef(CategorySectionItem.ITEM_TYPE_TEXT, R.layout.lib_item_category_sub_item_text);
        sectionAdapter.addItemTypeDef(CategorySectionItem.ITEM_TYPE_IMG_TEXT, R.layout.category_sub_item_img_text);
    }

    @Override
    public void convertMainCategoryItem(BaseViewHolder helper, com.sky.categorybrowser.model.Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> item) {
        helper.setText(R.id.category_title_tv, item.data.getStrName());
        ImageView imageView = helper.getView(R.id.category_ic_iv);
        if (!TextUtils.isEmpty(item.data.getPicurl())) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.data.getPicurl()).placeholder(R.drawable.ic_launcher_foreground).into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void convertSectionHead(BaseViewHolder baseViewHolder, CategorySectionItem<CategoryBean.LevelonesBean, CategoryItem> sectionHead) {
        super.convertSectionHead(baseViewHolder, sectionHead);
        ImageView icon = baseViewHolder.getView(R.id.category_sub_head_iv);
        if (!TextUtils.isEmpty(sectionHead.sectionHead.getPicurl())) {
            icon.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(sectionHead.sectionHead.getPicurl()).placeholder(R.drawable.ic_launcher_foreground).into(icon);
        } else {
            icon.setVisibility(View.GONE);
        }
    }

    @Override
    public void convertSectionItem(BaseViewHolder baseViewHolder, CategorySectionItem<CategoryBean.LevelonesBean, CategoryItem> sectionItem) {
//lib中定义的两种type正好对应这里的type值
        switch (sectionItem.getItemType()) {
            case CategorySectionItem.ITEM_TYPE_IMG_TEXT:
                baseViewHolder.setText(R.id.category_sub_item_title_tv, sectionItem.t.getStrName());
                Glide.with(mContext).load(sectionItem.t.getPicurl()).transition(new DrawableTransitionOptions().crossFade(400)).placeholder(R.drawable.ic_launcher_background).into((ImageView) baseViewHolder.getView(R.id.category_sub_item_ic_iv));
                break;
            case CategorySectionItem.ITEM_TYPE_TEXT:
                baseViewHolder.setText(R.id.category_sub_item_text_title_tv, sectionItem.t.getStrName());
                break;
        }
    }
}