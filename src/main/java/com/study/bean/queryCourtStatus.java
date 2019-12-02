package com.study.bean;

public class queryCourtStatus {

    private String name;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public queryCourtStatus(){}

    public queryCourtStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "queryCourtStatus{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
