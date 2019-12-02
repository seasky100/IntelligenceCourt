package com.study.bean;

public class ThreeIndexWeight {

    private String firstIndex;
    private double firstWeight;
    private String secIndex;
    private double secWeight;
    private String thirIndex;
    private double thirWeight;

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

    public String getSecIndex() {
        return secIndex;
    }

    public void setSecIndex(String secIndex) {
        this.secIndex = secIndex;
    }

    public double getSecWeight() {
        return secWeight;
    }

    public void setSecWeight(double secWeight) {
        this.secWeight = secWeight;
    }

    public String getThirIndex() {
        return thirIndex;
    }

    public void setThirIndex(String thirIndex) {
        this.thirIndex = thirIndex;
    }

    public double getThirWeight() {
        return thirWeight;
    }

    public void setThirWeight(double thirWeight) {
        this.thirWeight = thirWeight;
    }
    public ThreeIndexWeight(){}
    public ThreeIndexWeight(String firstIndex, double firstWeight, String secIndex, double secWeight, String thirIndex, double thirWeight) {
        this.firstIndex = firstIndex;
        this.firstWeight = firstWeight;
        this.secIndex = secIndex;
        this.secWeight = secWeight;
        this.thirIndex = thirIndex;
        this.thirWeight = thirWeight;
    }

    @Override
    public String toString() {
        return "ThreeIndexWeight{" +
                "firstIndex='" + firstIndex + '\'' +
                ", firstWeight=" + firstWeight +
                ", secIndex='" + secIndex + '\'' +
                ", secWeight=" + secWeight +
                ", thirIndex='" + thirIndex + '\'' +
                ", thirWeight=" + thirWeight +
                '}';
    }
}
