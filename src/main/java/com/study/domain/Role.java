package com.study.domain;

public class Role {
    private Integer rid;

    private String rnum;

    private String rname;

    private Double rweight;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum == null ? null : rnum.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public Double getRweight() {
        return rweight;
    }

    public void setRweight(Double rweight) {
        this.rweight = rweight;
    }
    public Role(){}
    public Role(Integer rid, String rnum, String rname, Double rweight) {
        this.rid = rid;
        this.rnum = rnum;
        this.rname = rname;
        this.rweight = rweight;
    }
}