package com.sky.categorydemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.categorybrowser.AbsCategoryViewConfiger;
import com.sky.categorybrowser.CategoryBrowserView;
import com.sky.categorybrowser.CategoryViewConfiger;
import com.sky.categorybrowser.adapter.CategorySectionAdapter;
import com.sky.categorybrowser.model.Category;
import com.sky.categorybrowser.model.CategorySectionItem;
import com.sky.categorydemo.repo.Repository;
import com.sky.categorydemo.repo.model.CategoryBean;
import com.sky.categorydemo.repo.model.CategoryItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupCategory();
    }

    private void setupCategory() {
        //1. 初始化一个CategoryBrowserView。 Setup the category view
        CategoryBrowserView<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> categoryBrowserView = findViewById(R.id.category_view);
        //2. 加载数据，转换成所需类型。 Load and convert your data to the structure required
        CategoryBean category = Repository.getSampleCategory();
        List<Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem>> categoryList = DemoCategoryViewConfiger.convertToCategory(category.getMainCategoryList());
        //3. 定义一个view configer, 设置数据展示及点击逻辑。  Define a view configer to define how to setup the category view  with your data
        CategoryViewConfiger<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> configer = new DemoCategoryViewConfiger(MainActivity.this){

            @Override
            public void onConfigSectionAdapter(CategorySectionAdapter<CategoryBean.LevelonesBean, CategoryItem> sectionAdapter, int position) {
                super.onConfigSectionAdapter(sectionAdapter, position);
                sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CategorySectionItem<CategoryBean.LevelonesBean,CategoryItem> item = sectionAdapter.getData().get(position);
                        if(item.isHeader){
                            Toast.makeText(getBaseContext(),"点击了标题"+item.sectionHead.getStrName(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getBaseContext(),"点击了条目"+item.t.getStrName(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        //4. 将view configer设置给初始化一个CategoryBrowserView，要先设置configer。Set the configer and data to the category view. Configer must be set before the data
        categoryBrowserView.setViewConfiger(configer);
        categoryBrowserView.setCategoryList(categoryList,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
