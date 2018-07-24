package com.pfl.common.entity.base;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by rocky on 2018/4/8.
 */
public abstract class BaseEntyty implements MultiItemEntity {

    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
