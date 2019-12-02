package com.study.domain;

public class Secondindex {
    private Integer sid;

    private String sname;

    private Double sweight;

    private Integer parentId;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Double getSweight() {
        return sweight;
    }

    public void setSweight(Double sweight) {
        this.sweight = sweight;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Secondindex(){}

    public Secondindex(Integer sid, String sname, Double sweight, Integer parentId) {
        this.sid = sid;
        this.sname = sname;
        this.sweight = sweight;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Secondindex{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sweight=" + sweight +
                ", parentId=" + parentId +
                '}';
    }
}