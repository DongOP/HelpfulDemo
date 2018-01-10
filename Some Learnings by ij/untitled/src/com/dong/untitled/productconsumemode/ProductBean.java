package com.dong.untitled.productconsumemode;

/**
 * Created by Dong on 2018/1/10 0010.
 */
public class ProductBean {
    private int id;
    private String name;

    public ProductBean() {
    }

    public ProductBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
