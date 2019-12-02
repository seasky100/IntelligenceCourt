package com.study.domain;

public class UserRoleRel {
    private Integer uid;

    private Integer rid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public UserRoleRel(){}

    public UserRoleRel(Integer uid, Integer rid) {
        this.uid = uid;
        this.rid = rid;
    }
}