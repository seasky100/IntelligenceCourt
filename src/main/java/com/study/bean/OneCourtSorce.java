package com.study.bean;

import java.util.HashMap;

/*存储单个法院得分情况*/
public class OneCourtSorce {

    private String name;//法院名称
    private double endSorce;//总得分

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEndSorce() {
        return endSorce;
    }

    public void setEndSorce(double endSorce) {
        this.endSorce = endSorce;
    }

    @Override
    public String toString() {
        return "OneCourtSorce{" +
                "name='" + name + '\'' +
                ", endSorce=" + endSorce +
                '}';
    }
}
