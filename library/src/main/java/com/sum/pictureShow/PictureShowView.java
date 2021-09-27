package com.sum.pictureShow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.sum.pictureShow.listener.OnAdapterClickListener;
import com.sum.pictureShow.listener.OnPictureAddListener;
import com.sum.pictureShow.listener.OnPictureClickListener;
import com.sum.pictureShow.listener.OnPictureDeleteListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liujiang
 * created at: 2021/9/26 16:13
 * Desc: 图片选择显示控件
 */
public class PictureShowView extends RecyclerView {
    public final static int ADD = 0;
    public final static int IMAGE = 1;
    /**
     * 默认列数
     */
    private final int defaultColumn = 3;

    /**
     * 默认图片总数上限
     */
    private final int defaultCount = 6;

    /**
     * 默认item之间间隔
     */
    private final int defaultDivider = 12;

    /**
     * 删除按钮默认尺寸
     */
    private final int defaultDelSize = 20;

    /**
     * 删除按钮图片
     */
    private int iconDel;

    /**
     * 是否显示删除按钮
     */
    private boolean isDelShow;

    /**
     * 是否显示添加按钮
     */
    private boolean isAddShow;

    /**
     * 新增按钮图片
     */
    private int iconAdd;

    /**
     * 图片总数
     */
    private int totalCount;

    /**
     *新增图片按钮监听
     */
    private OnPictureAddListener onPictureAddListener;

    /**
     * 图片点击按钮监听
     */
    private OnPictureClickListener onPictureClickListener;

    /**
     * 删除图片按钮监听
     */
    private OnPictureDeleteListener onPictureDeleteListener;

    /**
     * item之间的间距
     */
    private int itemDiv;

    /**
     * 删除按钮尺寸
     */
    private int delSize;

    /**
     * 列数
     */
    private int column;

    private final List<ItemPicture> data = new ArrayList<>();

    private PictureAdapter adapter;



    public PictureShowView(@NonNull Context context) {
        super(context);
    }

    public PictureShowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //计算控件宽度
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                initView(context, getWidth());
                return true;
            }
        });
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PictureShowView);
        //列数
        column = typedArray.getInteger(R.styleable.PictureShowView_ps_column, defaultColumn);
        GridLayoutManager layoutManager = new GridLayoutManager(context, column);
        setLayoutManager(layoutManager);
        //图片总数
        totalCount = typedArray.getInteger(R.styleable.PictureShowView_ps_count, defaultCount);
        //item之间的间距
        itemDiv = (int) typedArray.getDimension(R.styleable.PictureShowView_ps_divider_size, ToolsUtils.dip2px(context, defaultDivider));
        //删除按钮图片
        iconDel = typedArray.getResourceId(R.styleable.PictureShowView_ps_icon_delete, R.mipmap.image_del_ic);
        //删除按钮尺寸
        delSize = (int) typedArray.getDimension(R.styleable.PictureShowView_ps_icon_delete_size, ToolsUtils.dip2px(context, defaultDelSize));
        //新增按钮图片
        iconAdd = typedArray.getResourceId(R.styleable.PictureShowView_ps_icon_add, R.mipmap.camera);
        //是否显示删除按钮
        isDelShow = typedArray.getBoolean(R.styleable.PictureShowView_ps_show_del, true);
        //是否显示添加按钮
        isAddShow = typedArray.getBoolean(R.styleable.PictureShowView_ps_show_add, true);
        if (isAddShow) {
            data.add(new ItemPicture(ADD, iconAdd));
        }

        typedArray.recycle();
    }

    /**
     * 初始化
     *
     * @param context
     * @param width
     */
    private void initView(Context context, int width) {
        //每个item的尺寸
        int itemSize = width / column;

        adapter = new PictureAdapter(context, totalCount, data, itemSize, itemDiv, delSize);
        setAdapter(adapter);

        adapter.setOnItemClickListener(new OnAdapterClickListener() {
            @Override
            public void onAdd() {
                if (null != onPictureAddListener) {
                    onPictureAddListener.onAdd();
                }
            }

            @Override
            public void onPictureClick(int position) {
                if (null != onPictureClickListener) {
                    String path = data.get(position).getPath();
                    List<String> paths = new ArrayList<>();
                    for (ItemPicture item : data) {
                        paths.add(item.getPath());
                    }
                    onPictureClickListener.onClick(position, path, paths);
                }
            }

            @Override
            public void onDelete(int position) {
                if (null != onPictureDeleteListener) {
                    onPictureDeleteListener.onDelete(position);
                } else {
                    remove(position);
                }
            }
        });
    }

    /**
     * 添加图片
     *
     * @param path 图片路径
     */
    public void add(String path) {
        if (isAddShow) {
            data.remove(data.size() - 1);
            data.add(new ItemPicture(path, iconDel, isDelShow));
            data.add(new ItemPicture(ADD, iconAdd));
        } else {
            data.add(new ItemPicture(path, iconDel, isDelShow));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 添加图片
     *
     * @param path      图片路径
     * @param isDelShow 是否显示删除按钮
     */
    public void add(String path, boolean isDelShow) {
        if (isAddShow) {
            data.remove(data.size() - 1);
            data.add(new ItemPicture(path, iconDel, isDelShow));
            data.add(new ItemPicture(ADD, iconAdd));
        } else {
            data.add(new ItemPicture(path, iconDel, isDelShow));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 添加图片
     *
     * @param paths 图片路径
     */
    public void add(List<String> paths) {
        if (isAddShow) {
            data.remove(data.size() - 1);
        }
        if (null != paths && paths.size() > 0) {
            for (String path : paths) {
                data.add(new ItemPicture(path, iconDel, isDelShow));
            }
        }
        if (isAddShow) {
            data.add(new ItemPicture(ADD, iconAdd));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 添加图片
     *
     * @param paths     图片路径
     * @param isDelShow 是否显示删除按钮
     */
    public void add(List<String> paths, boolean isDelShow) {
        if (isAddShow) {
            data.remove(data.size() - 1);
        }
        if (null != paths && paths.size() > 0) {
            for (String path : paths) {
                data.add(new ItemPicture(path, iconDel, isDelShow));
            }
        }
        if (isAddShow) {
            data.add(new ItemPicture(ADD, iconAdd));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 移除图片
     *
     * @param position
     */
    public void remove(int position) {
        if (position < data.size()) {
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, data.size() - position);
            data.remove(position);
        }
    }

    /**
     * 清空图片
     */
    public void clear() {
        data.clear();
        if (isAddShow) {
            data.add(new ItemPicture(ADD, iconAdd));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取所有图片列表
     * @return
     */
    public List<String> getPictureList() {
        List<String> paths = new ArrayList<>();
        for (ItemPicture item : data) {
            paths.add(item.getPath());
        }
        return paths;
    }

    /**
     * 新增图片按钮监听
     *
     * @param onPictureAddListener
     */
    public void setOnPictureAddListener(OnPictureAddListener onPictureAddListener) {
        this.onPictureAddListener = onPictureAddListener;
    }

    /**
     * 图片点击监听
     *
     * @param onPictureClickListener
     */
    public void setOnPictureClickListener(OnPictureClickListener onPictureClickListener) {
        this.onPictureClickListener = onPictureClickListener;
    }

    /**
     * 图片删除按钮监听
     *
     * @param onPictureDeleteListener
     */
    public void setOnPictureDeleteListener(OnPictureDeleteListener onPictureDeleteListener) {
        this.onPictureDeleteListener = onPictureDeleteListener;
    }
}
