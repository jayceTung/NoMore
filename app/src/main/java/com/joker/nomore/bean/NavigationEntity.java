package com.joker.nomore.bean;

import com.joker.nomore.base.BaseEntity;

/**
 * Created by Joker on 2015/10/16.
 */
public class NavigationEntity extends BaseEntity {
    private int resId;


    public NavigationEntity(String name, String id, int resId) {
        super(name, id);
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
