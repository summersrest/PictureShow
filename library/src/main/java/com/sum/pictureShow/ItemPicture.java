package com.sum.pictureShow;

/**
 * @author liujiang
 * created at: 2021/9/26 17:19
 * Desc:
 */
public class ItemPicture {
    private int type;
    private String path;
    private int iconDel;
    private int iconAdd;
    private boolean isDelShow;

    public ItemPicture(int type, int iconAdd) {
        this.type = type;
        this.iconAdd = iconAdd;
        this.isDelShow = false;
    }

    public ItemPicture(String path, int iconDel, boolean isDelShow) {
        this.type = PictureShowView.IMAGE;
        this.path = path;
        this.iconDel = iconDel;
        this.isDelShow = isDelShow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIconDel() {
        return iconDel;
    }

    public void setIconDel(int iconDel) {
        this.iconDel = iconDel;
    }

    public int getIconAdd() {
        return iconAdd;
    }

    public void setIconAdd(int iconAdd) {
        this.iconAdd = iconAdd;
    }

    public boolean isDelShow() {
        return isDelShow;
    }

    public void setDelShow(boolean delShow) {
        isDelShow = delShow;
    }
}
