# PictureShow
选择图片后展示图片
![1](https://github.com/summersrest/PictureShow/blob/master/img/S10928-10012819.png)
## **1、导入**
 1.引入jitpack
 ```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2.添加
```java
implementation 'com.github.summersrest:PictureShow:v1.0.0'
```
## **2、使用**
### 1、xml中使用
```xml
<com.sum.pictureShow.PictureShowView
        android:id="@+id/psv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:ps_column="4"
        app:ps_count="6"
        app:ps_divider_size="15dp"
        app:ps_icon_add="@mipmap/camera"
        app:ps_icon_delete="@mipmap/image_del_ic"
        app:ps_icon_delete_size="20dp"
        app:ps_show_add="true"
        app:ps_show_del="true" />
```
### Attributes
|name|format|description|
|:---:|:---:|:---:|
| ps_count  | integer |图片总数上限
| ps_column  | integer  |显示列数
| ps_divider_size | dimension  |item之间的间隔
| ps_icon_delete | reference  |删除按钮图片
| ps_icon_delete_size | dimension  |删除按钮尺寸
| ps_icon_add | reference  |新增按钮图片
| ps_show_add | boolean  |是否显示添加按钮
| ps_show_del | boolean  |是否显示删除按钮

```java
//图片新增
pictureView.setOnPictureAddListener(new OnPictureAddListener() {
    @Override
    public void onAdd() {
        //添加单张图片
        pictureView.add(path1);
        //添加单张图片，并且显示删除按钮（会覆盖xml中的全局配置）
        pictureView.add(path2, true);
        //添加多张按钮
        pictureView.add(Arrays.asList(path3, path4));
        //添加多张图片，并且显示删除按钮
        pictureView.add(Arrays.asList(path5, path6), true);
    }
});

//图片点击监听
pictureView.setOnPictureClickListener(new OnPictureClickListener() {

    /**
     * 图片点击事件
     * @param position  位置
     * @param path      被点击图片路径
     * @param paths     所有图片路径列表
     */
    @Override
    public void onClick(int position, String path, List<String> paths) {
        Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
    }
});

//图片删除监听（添加该监听后，需手动调用.remove()方法来删除item，若不需要特殊操作，无需添加该监听事件）
pictureView.setOnPictureDeleteListener(new OnPictureDeleteListener() {
    @Override
    public void onDelete(int position) {
        pictureView.remove(position);

    }
});

//清空所有图片
pictureView.clear();
```
