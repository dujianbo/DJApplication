package com.personal.djb.catmovie.bean.cinema.cinemamenu;

import java.util.List;

/**
 * 二级分类，相当于右侧菜单
 * Created by hanj on 14-9-25.
 */
public class SecondClassItem {
    private int id;
    private String name;
    private List<ThirdClassItem> thirdList;

    public SecondClassItem(){

    }

    public SecondClassItem(int id, String name, List<ThirdClassItem> thirdList) {
        this.id = id;
        this.name = name;
        this.thirdList = thirdList;
    }

    public List<ThirdClassItem> getThirdList() {
        return thirdList;
    }

    public void setThirdList(List<ThirdClassItem> thirdList) {
        this.thirdList = thirdList;
    }

    public SecondClassItem(int id, String name) {
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
        return "SecondClassItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
