package com.sum.pictureShow.listener;

/**
 * @author liujiang
 * created at: 2021/9/27 9:40
 * Desc:
 */
public interface OnAdapterClickListener {
    void onAdd();

    void onPictureClick(int position);

    void onDelete(int position);
}
