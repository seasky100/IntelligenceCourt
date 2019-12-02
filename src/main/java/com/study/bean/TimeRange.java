package com.study.bean;
/*存储前台传入查询时间范围*/
public class TimeRange {

    private String start_time;
    private String end_time;
    private String init_time;
    private String init_time_add;

    public String getInit_time_add() {
        return init_time_add;
    }

    public void setInit_time_add(String init_time_add) {
        this.init_time_add = init_time_add;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInit_time() {
        return init_time;
    }

    public void setInit_time(String init_time) {
        this.init_time = init_time;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", init_time='" + init_time + '\'' +
                ", init_time_add='" + init_time_add + '\'' +
                '}';
    }
}
