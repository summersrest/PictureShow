package com.sum.pictureShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sum.pictureShow.listener.OnAdapterClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liujiang
 * created at: 2021/9/26 17:29
 * Desc:
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private final Context context;
    //可添加图片总数
    private final int totalCount;
    //数据源
    private final List<ItemPicture> data;
    //每个item的大小
    private final int itemSize;
    //item之间的间隔
    private final int itemDiv;
    //删除图标的尺寸
    private final int delIconSize;

    private OnAdapterClickListener listener;

    public PictureAdapter(Context context, int totalCount, List<ItemPicture> data, int itemSize, int itemDiv, int delIconSize) {
        this.context = context;
        this.totalCount = totalCount;
        this.data = data;
        this.itemSize = itemSize;
        this.itemDiv = itemDiv;
        this.delIconSize = delIconSize;
    }

    public void setOnItemClickListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picture_show_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPicture item = data.get(position);
        //设置每个item大小
        ToolsUtils.setViewSize(holder.itemLayout, itemSize, itemSize);
        //图片与父layout之间的margin
        int imgMargin = itemDiv / 2;
        //设置图片大小
        ToolsUtils.setViewSize(holder.ivPicture, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, imgMargin);

        if (item.getType() == PictureShowView.ADD) {
            //新增
            holder.ivPicture.setImageResource(item.getIconAdd());
            holder.ivDel.setVisibility(View.GONE);
        } else if (item.getType() == PictureShowView.IMAGE){
            Glide.with(context).load(item.getPath()).into(holder.ivPicture);
            //删除按钮是否显示
            if (item.isDelShow()) {
                holder.ivDel.setVisibility(View.VISIBLE);
                //删除图标
                holder.ivDel.setImageResource(item.getIconDel());
                //设置删除图标大小
                ToolsUtils.setViewSize(holder.ivDel, delIconSize, delIconSize, itemDiv / 2);
                holder.ivDel.setOnClickListener(v -> {
                    if (null != listener) {
                        listener.onDelete(position);
                    }
                });
            } else {
                holder.ivDel.setVisibility(View.GONE);
            }
        }

        holder.ivPicture.setOnClickListener(v -> {
            switch (item.getType()) {
                case PictureShowView.ADD:
                    if (null != listener) {
                        listener.onAdd();
                    }
                    break;
                case PictureShowView.IMAGE:
                    if (null != listener) {
                        listener.onPictureClick(position);
                    }
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(data.size(), totalCount);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivPicture;
        private final ImageView ivDel;
        private final FrameLayout itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.image_view);
            ivDel = itemView.findViewById(R.id.iv_del);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
} 
