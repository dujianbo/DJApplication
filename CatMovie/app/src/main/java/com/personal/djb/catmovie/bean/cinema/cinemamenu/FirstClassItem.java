package com.personal.djb.catmovie.bean.cinema.cinemamenu;

import java.util.List;

/**
 * 一级分类，相当于左侧菜单
 * Created by hanj on 14-9-25.
 */
public class FirstClassItem {
    private int id;
    private String name;
    private List<SecondClassItem> secondList;

    public FirstClassItem(){

    }

    public FirstClassItem(int id, String name, List<SecondClassItem> secondList) {
        this.id = id;
        this.name = name;
        this.secondList = secondList;
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

    public List<SecondClassItem> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<SecondClassItem> secondList) {
        this.secondList = secondList;
    }

    @Override
    public String toString() {
        return "FirstClassItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondList=" + secondList +
                '}';
    }
}
