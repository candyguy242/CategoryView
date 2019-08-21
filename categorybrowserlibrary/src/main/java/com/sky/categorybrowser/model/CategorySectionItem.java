package com.sky.categorybrowser.model;

import com.chad.library.adapter.base.entity.SectionMultiEntity;

/**
 * 描述：
 *
 * @author renpeng
 * @since 2019/5/28
 */
public class CategorySectionItem<SectionHead,SectionItem> extends SectionMultiEntity<SectionItem> {

    //定义两种默认的Type
    public static final int ITEM_TYPE_TEXT = 0;
    public static final int ITEM_TYPE_IMG_TEXT = 1;

    public SectionHead sectionHead;
    private int type = 0;

    public CategorySectionItem(boolean isHeader, String header,SectionHead head) {
        super(isHeader, header);
        this.sectionHead = head;
    }

    public CategorySectionItem(SectionHead head,SectionItem sectionItem, int type) {
        super(sectionItem);
        this.sectionHead = head;
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
