package com.sum.pictureshowsample;

import android.os.Bundle;
import android.widget.Toast;
import com.sum.pictureShow.PictureShowView;
import com.sum.pictureShow.listener.OnPictureAddListener;
import com.sum.pictureShow.listener.OnPictureClickListener;
import com.sum.pictureShow.listener.OnPictureDeleteListener;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    PictureShowView pictureView;
    private final String path1 = "/storage/emulated/0/DCIM/P10310-111127.jpg";
    private final String path2 = "/storage/emulated/0/DCIM/P10310-110903.jpg";
    private final String path3 = "/storage/emulated/0/DCIM/P10310-110859.jpg";
    private final String path4 = "/storage/emulated/0/DCIM/P10310-085745.jpg";
    private final String path5 = "/storage/emulated/0/DCIM/P10310-085752.jpg";
    private final String path6 = "/storage/emulated/0/DCIM/P10310-092631.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureView = findViewById(R.id.psv);

        //图片新增
        pictureView.setOnPictureAddListener(new OnPictureAddListener() {
            @Override
            public void onAdd() {
                //添加单张图片
                pictureView.add(path1);
                pictureView.clearAndAdd(path1);
                //添加单张图片，并且显示删除按钮（会覆盖xml中的全局配置）
                pictureView.add(path2, true);
                pictureView.clearAndAdd(path2, true);
                //添加多张按钮
                pictureView.add(Arrays.asList(path3, path4));
                pictureView.clearAndAdd(Arrays.asList(path3, path4));
                //添加多张图片，并且显示删除按钮
                pictureView.add(Arrays.asList(path5, path6), true);
                pictureView.clearAndAdd(Arrays.asList(path5, path6), true);
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
//        pictureView.clear();

    }
}