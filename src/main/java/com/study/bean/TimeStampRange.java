package com.study.bean;

import java.sql.Timestamp;

public class TimeStampRange {
    private Timestamp start;
    private Timestamp end;

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeStampRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
