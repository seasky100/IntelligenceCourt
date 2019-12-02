package com.study.bean;

public class OneCitySorce {

    private String name;
    private double endSorce;

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
        return "OneCitySorce{" +
                "name='" + name + '\'' +
                ", endSorce=" + endSorce +
                '}';
    }
}
