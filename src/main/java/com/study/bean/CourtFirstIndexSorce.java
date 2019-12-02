package com.study.bean;

public class CourtFirstIndexSorce {
    private String court;
    private String firstIndexName;
    private double sorce;

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getFirstIndexName() {
        return firstIndexName;
    }

    public void setFirstIndexName(String firstIndexName) {
        this.firstIndexName = firstIndexName;
    }

    public double getSorce() {
        return sorce;
    }

    public void setSorce(double sorce) {
        this.sorce = sorce;
    }

    @Override
    public String toString() {
        return "CourtFirstIndexSorce{" +
                "court='" + court + '\'' +
                ", firstIndexName='" + firstIndexName + '\'' +
                ", sorce=" + sorce +
                '}';
    }
}
