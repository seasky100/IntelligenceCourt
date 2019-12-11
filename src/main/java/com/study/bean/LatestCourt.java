package com.study.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/*
* 最新被评分的法院
* */
public class LatestCourt {
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date time;
    private String name;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LatestCourt{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}
