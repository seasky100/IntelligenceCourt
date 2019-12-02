package com.study.bean;

public class ThreeIndex {

    private int fid;
    private String fname;
    private int sid;
    private String sname;
    private int sparentid;
    private int tid;
    private String tname;
    private int tparentid;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSparentid() {
        return sparentid;
    }

    public void setSparentid(int sparentid) {
        this.sparentid = sparentid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getTparentid() {
        return tparentid;
    }

    public void setTparentid(int tparentid) {
        this.tparentid = tparentid;
    }

    @Override
    public String toString() {
        return "ThreeIndex{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sparentid=" + sparentid +
                ", tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tparentid=" + tparentid +
                '}';
    }
}
