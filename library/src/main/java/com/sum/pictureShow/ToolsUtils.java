package com.sum.pictureShow;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * @author liujiang
 * created at: 2021/9/23 9:50
 * Desc:
 */
public class ToolsUtils {
    /**
     * dp转px
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }



    /**
     * 设置控件宽高
     * @param view
     * @param width
     * @param height
     */
    public static void setViewSize(View view, int width, int height) {
        ViewGroup.LayoutParams lp =  view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);
    }

    /**
     * 设置控件宽高
     * @param view
     * @param width
     * @param margin
     */
    public static void setViewSize(View view, int width, int height, int margin) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        lp.setMargins(margin, margin, margin, margin);
        view.setLayoutParams(lp);
    }
} 
