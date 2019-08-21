package com.sky.categorybrowser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sky.categorybrowser.model.Category;

import java.util.List;

/**
 * 两列并排的目录浏览控件,三级目录结构，主目录展示为纵向列表，子目录在右侧展示为标题+网格，支持viewpager式浏览<br>
 * 先调用{@link #setViewConfiger(CategoryViewConfiger)}，viewconfiger可继承自{@link AbsCategoryViewConfiger}<br>
 * 然后调用{@link #setCategoryList(List, int)}来设置展示的数据<br>
 *
 * @author renpeng
 * @since 2019/5/27
 */
public class CategoryBrowserView<CategoryMain, SectionHead, SectionItem> extends LinearLayout {

    private RecyclerView mainCategoryRv;
    private RecyclerView subCategoryRv;
    private List<Category<CategoryMain, SectionHead, SectionItem>> mCategoryList;
    private CategoryViewConfiger mViewConfiger;
    private CategoryViewController mViewController;

    public CategoryBrowserView(Context context) {
        this(context,null);
    }

    public CategoryBrowserView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CategoryBrowserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CategoryBrowserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs) {

        setOrientation(HORIZONTAL);

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.view_category_browser,this,false);

        mainCategoryRv = (RecyclerView) root.findViewById(R.id.category_left_rv);
        subCategoryRv = (RecyclerView)  root.findViewById(R.id.category_right_rv);

        ViewGroup.LayoutParams mainCategoryRvLayoutParams = mainCategoryRv.getLayoutParams();
        TypedArray ta =  getContext().obtainStyledAttributes(attrs, R.styleable.CategoryBrowserView);
        int mainWidth = ta.getDimensionPixelSize(R.styleable.CategoryBrowserView_mainWidth,mainCategoryRvLayoutParams.width);
        mainCategoryRvLayoutParams.width = mainWidth;
        ta.recycle();

        root.removeView(mainCategoryRv);
        root.removeView(subCategoryRv);
        mainCategoryRv.setLayoutParams(mainCategoryRvLayoutParams);

        addView(mainCategoryRv);
        addView(subCategoryRv);

        mainCategoryRv.setHasFixedSize(true);
        subCategoryRv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mainCategoryRv.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        subCategoryRv.setLayoutManager(linearLayoutManager2);
    }

    public RecyclerView getMainCategoryRv() {
        return mainCategoryRv;
    }

    public RecyclerView getSubCategoryRv() {
        return subCategoryRv;
    }

    /**
     * 返回目录数据列表
     */
    public List<Category<CategoryMain, SectionHead, SectionItem>> getCategoryList(){
        return mCategoryList;
    }

    public final void setCategoryList(List<Category<CategoryMain, SectionHead, SectionItem>> categoryList, int selected) {
        if(mViewConfiger == null){
            throw new RuntimeException("view configer can't be null，call setViewConfiger first!!");
        }
        mCategoryList = categoryList;
        if(mViewController == null){
            mViewController = new CategoryViewController(this,mViewConfiger);
        }
        mViewController.setCategoryList(categoryList,selected);
    }

    public void setViewConfiger(CategoryViewConfiger viewConfiger) {
        this.mViewConfiger = viewConfiger;
    }

    public int getSelectedCategoryIndex() {
        return mViewController != null ? mViewController.getSelectedCategoryIndex() : 0;
    }
}
