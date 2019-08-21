package com.sky.categorybrowser.model;

import java.util.List;

/**
 * 描述：一级目录列表项结构
 *
 * @author renpeng
 * @since 2019/5/28
 */
public class Category<CategoryMain,SectionHead,SectionItem> {

    public CategoryMain data;

    public Category(CategoryMain data) {
        this.data = data;
    }

    private List<CategorySectionItem<SectionHead,SectionItem>> categorySectionList;

    public List<CategorySectionItem<SectionHead,SectionItem>> getCategorySectionList() {
        return categorySectionList;
    }

    public void setCategorySectionList(List<CategorySectionItem<SectionHead,SectionItem>> categorySectionList) {
        this.categorySectionList = categorySectionList;
    }
}
