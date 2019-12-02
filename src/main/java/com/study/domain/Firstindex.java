package com.study.domain;

public class Firstindex {
    private Integer fid;

    private String fname;

    private Double fweight;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Double getFweight() {
        return fweight;
    }

    public void setFweight(Double fweight) {
        this.fweight = fweight;
    }

    public Firstindex(){}

    public Firstindex(Integer fid, String fname, Double fweight) {
        this.fid = fid;
        this.fname = fname;
        this.fweight = fweight;
    }

    @Override
    public String toString() {
        return "Firstindex{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", fweight=" + fweight +
                '}';
    }
}