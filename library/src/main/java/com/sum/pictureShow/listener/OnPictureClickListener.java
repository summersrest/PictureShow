package com.sum.pictureShow.listener;

import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/27 13:34
 * Desc:
 */
public interface OnPictureClickListener {
    void onClick(int position, String path, List<String> paths);
}
