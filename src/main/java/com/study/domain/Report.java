package com.study.domain;

import java.util.Date;

public class Report {
    private String cname;

    private Integer uid;

    private String tname;

    private Double tsorce;

    private Date time;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Double getTsorce() {
        return tsorce;
    }

    public void setTsorce(Double tsorce) {
        this.tsorce = tsorce;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}