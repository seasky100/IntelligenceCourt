package com.study.bean;

public class IndexRelation {

    private String firstIndex;
    private double firstWeight;
    private String secondIndex;
    private double secondWeight;
    private String thirdIndex;
    private double thirdWeight;

    public String getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(String firstIndex) {
        this.firstIndex = firstIndex;
    }

    public double getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(double firstWeight) {
        this.firstWeight = firstWeight;
    }

    public String getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(String secondIndex) {
        this.secondIndex = secondIndex;
    }

    public double getSecondWeight() {
        return secondWeight;
    }

    public void setSecondWeight(double secondWeight) {
        this.secondWeight = secondWeight;
    }

    public String getThirdIndex() {
        return thirdIndex;
    }

    public void setThirdIndex(String thirdIndex) {
        this.thirdIndex = thirdIndex;
    }

    public double getThirdWeight() {
        return thirdWeight;
    }

    public void setThirdWeight(double thirdWeight) {
        this.thirdWeight = thirdWeight;
    }

    @Override
    public String toString() {
        return "IndexRelation{" +
                "firstIndex='" + firstIndex + '\'' +
                ", firstWeight=" + firstWeight +
                ", secondIndex='" + secondIndex + '\'' +
                ", secondWeight=" + secondWeight +
                ", thirdIndex='" + thirdIndex + '\'' +
                ", thirdWeight=" + thirdWeight +
                '}';
    }
}
