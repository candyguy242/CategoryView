安卓目录View，双列展示。

Android category view, displayed as two list. 

## 特点 Feature:

1. 可配置性高，条目样式可以通过multi type的方式拓展，分块间距可以通过decoration来设置，感谢 [BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)~

2. 滚动时自动同步两列的位置;

3. 反射滚动完全支持,此时前一列表会从最后一项开始显示;

4. 泛型支持，设置时更易获取数据;

5. 使用方便~

   

1. Highly configurable, the item style and section margin can be extended by multi type and decoration, supported by [BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper),thanks to them~
2. Auto sync position in two list when scrolling;
3. Backward scroll is fully supported, previous list is displayed from the last one;
4. Generic support, easy to get your data when configuring;
5. Very easy to use~
   

示例截图 Demo capture:

![see the demo folder](https://raw.githubusercontent.com/candyguy242/CategroyView/master/demo/device-2019-08-21-175038.png)

示例视频 Demo video:
[点击查看 Click to see video](https://raw.githubusercontent.com/candyguy242/CategroyView/master/demo/device-2019-08-20-135249.mp4)

使用方式 Way to use:

```xml
<com.sky.categorybrowser.CategoryBrowserView
android:id="@+id/category_view"
android:layout_width="match_parent"
android:layout_height="match_parent"
app:mainWidth="100dp"
/>

```
```java
private void setupCategory() {
    //1. 初始化一个CategoryBrowserView。 Setup the category view
    CategoryBrowserView<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> categoryBrowserView = findViewById(R.id.category_view);
    //2. 加载数据，转换成所需类型。 Load and convert your data to the structure required
    CategoryBean category = Repository.getSampleCategory();
    List<Category<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem>> categoryList = DemoCategoryViewConfiger.convertToCategory(category.getMainCategoryList());
    //3. 定义一个view configer, 设置数据展示及点击逻辑。  Define a view configer to define how to setup the category view  with your data
    CategoryViewConfiger<CategoryBean.MainCategory, CategoryBean.LevelonesBean, CategoryItem> configer = new DemoCategoryViewConfiger(MainActivity.this){
        .....
    };
    //4. 将view configer设置给初始化一个CategoryBrowserView，要先设置configer。Set the configer and data to the category view. Configer must be set before the data
    categoryBrowserView.setViewConfiger(configer);
    categoryBrowserView.setCategoryList(categoryList,0);
    }
```
