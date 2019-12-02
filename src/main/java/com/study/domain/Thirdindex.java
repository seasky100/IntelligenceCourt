package com.study.domain;

public class Thirdindex {
    private Integer tid;

    private String tname;

    private Double tweight;

    private Integer parentId;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Double getTweight() {
        return tweight;
    }

    public void setTweight(Double tweight) {
        this.tweight = tweight;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Thirdindex(){}

    public Thirdindex(Integer tid, String tname, Double tweight, Integer parentId) {
        this.tid = tid;
        this.tname = tname;
        this.tweight = tweight;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Thirdindex{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tweight=" + tweight +
                ", parentId=" + parentId +
                '}';
    }
}