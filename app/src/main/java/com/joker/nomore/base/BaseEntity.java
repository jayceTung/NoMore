package com.joker.nomore.base;

/**
 * Created by Joker on 2015/10/15.
 */
public class BaseEntity {
    private String name;
    private String id;

    public BaseEntity(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
